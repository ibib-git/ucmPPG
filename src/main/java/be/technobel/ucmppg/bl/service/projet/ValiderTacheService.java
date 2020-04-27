package be.technobel.ucmppg.bl.service.projet;

import be.technobel.ucmppg.Exception.ErrorServiceException;
import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.configuration.Constantes;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ValiderTacheService {

    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private DroitProjetRepository droitProjetRepository;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private HistoriqueTacheRepository historiqueTacheRepository;
    @Autowired
    private EtapeWorkflowRepository etapeWorkflowRepository;
    @Autowired
    private ParticipationRepository participationRepository;

    // Beaucoup plus opti en chargeant une fois le projet en entier mais but du jour = exo revision de sql
    public ProjetDTO validerTache (long idUtilisateur, long idTache) throws ErrorServiceException {

        Optional<TacheEntity> tache = tacheRepository.findById(idTache);
        Optional<Long> tacheParente = tacheRepository.getIdTacheParent(idTache);

        // Verifier si il s'agit d'une tache enfant et si oui chercher l idProjet de la tache parente
        Long idProjet = tacheParente.isPresent() ? projetRepository.getIdProjetEntityByTacheEnfant(idTache) : projetRepository.getIdProjetEntityByTacheParent(idTache) ;
        EtapeWorkflowEntity etapeCourante = tacheParente.isPresent()? etapeWorkflowRepository.getEtapeByIdTacheEnfant(idTache) : etapeWorkflowRepository.getEtapeByIdTache(idTache);

        //Verifier que l'utilisateur existe et a le droit
        if (verificationDroitUtilisateurService(idUtilisateur, Constantes.DROIT_VALIDER_TACHE,idProjet))
        {
            Optional<EtapeWorkflowEntity> optionaleEtapeWorkflowSuivanteEntity = etapeWorkflowRepository.getNextEtapeByIdProjetAndEtape(idProjet,etapeCourante.getNumOrdreEtapeWorkflow());
            // check si l'étape courante est la dernière étape
            if (optionaleEtapeWorkflowSuivanteEntity.isPresent())
            {
                EtapeWorkflowEntity etapeWorkflowSuivanteEntity = optionaleEtapeWorkflowSuivanteEntity.get();
                Long idDerniereEtape = etapeWorkflowRepository.getIdDernièreEtapeByIdProjet(idProjet);

                // true de base car on ne sait pas si la tache possède des précédentes/enfants
                boolean estValidePrecedentes = true;
                boolean estValideEnfants = true;


                // Vérifier si il y a des taches précédentes et si elles sont bien toutes validée pour l'étape
                Optional<List<TacheEntity>> tachePrecedenteEntityList = tacheRepository.getAllTachePrecedente(idTache);
                Optional<List<HistoriqueTacheEntity>> historiqueTachePrecedenteEntityList = historiqueTacheRepository.getAllTachePrecedenteValidee(idTache);

                // par définition quand une tache est créée elle appartient déjà à une étape et donc possède un histo dès sa création
                if (tachePrecedenteEntityList.isPresent() && historiqueTachePrecedenteEntityList.isPresent())
                {
                    // c est l id de la dernière étape qui definit si une tache précédente est validée pour la tache actuelle
                    estValidePrecedentes = verificationValidationTacheSpecialHistorique(historiqueTachePrecedenteEntityList.get(),tachePrecedenteEntityList.get(),idDerniereEtape);
                }

                // Vérifier si il y a des taches enfants et si elles sont bien toutes validée pour l'étape
                Optional<List<TacheEntity>> tacheEnfantEntityList = tacheRepository.getAllTacheEnfant(idTache);
                Optional<List<HistoriqueTacheEntity>> historiqueTacheEnfantList = historiqueTacheRepository.getAllTacheEnfantValidee(idTache);

                if (tacheEnfantEntityList.isPresent() && historiqueTacheEnfantList.isPresent())
                {
                    estValideEnfants = verificationValidationTacheSpecialHistorique(historiqueTacheEnfantList.get(),tacheEnfantEntityList.get(),etapeWorkflowSuivanteEntity.getIdEtapeWorkflow());
                }

                // check si toutes les conditions sont remplies pour valider la tache
                if (estValideEnfants && estValidePrecedentes)
                {

                    HistoriqueTacheEntity historiqueTacheEntity = new HistoriqueTacheEntity();
                    Optional<UtilisateurEntity> utilisateurEntity = utilisateurRepository.findById(idUtilisateur);

                    historiqueTacheEntity.setEtapeWorkflowTacheHistorique(etapeWorkflowSuivanteEntity);
                    historiqueTacheEntity.setTacheHistorique(tache.get());
                    historiqueTacheEntity.setUtilisateur_Tache_historique(utilisateurEntity.get());

                    historiqueTacheRepository.save(historiqueTacheEntity);

                    // si elle a une tache parente alors elle doit juste être ajouté à l'historique pour l etape suivante
                    // car seul une tache sans parent est référencé dans une etape
                    if (!tacheParente.isPresent())
                    {
                        // déplacer la tache à l'étape suivante

                        etapeCourante.getTaches().remove(tache.get());
                        etapeWorkflowSuivanteEntity.getTaches().add(tache.get());
                        etapeWorkflowRepository.save(etapeCourante);
                        etapeWorkflowRepository.save(etapeWorkflowSuivanteEntity);
                    }

                    return new ProjetDTO(projetRepository.findById(idProjet).get());



                } // toutes les taches enfants et/ou taches précédentes ne sont pas validées
               else {
                   String errorMessage = null;

                    if (!estValideEnfants) {
                        errorMessage = "Toutes les taches enfants ne sont pas validee";
                    } else errorMessage = "Toutes les taches précédentes ne sont pas validee";

                    throw  new ErrorServiceException("Valider Tache",errorMessage);
                }
            } // l etape actuelle est la dernière étape donc pas de validation possible
            else  throw  new ErrorServiceException("Valider Tache","La tache se trouvent dans la dernière étape, il n'y a donc plus de validation possible");


        } // user n a pas les droits
        else  throw  new ErrorServiceException("Valider Tache","L utilisateur n'a pas les droits nécessaires pour cette action");

    }

    //TODO TOKEN : service a généraliser avec le check du token
    //TODO DAMIEN : a modifier avec le isActif de thomas à partir de participation et pas du user alors
    private boolean verificationDroitUtilisateurService (long idUtilisateur, String droit, long idProjet)
    {
        Optional<UtilisateurEntity> optionalUtilisateurEntity = utilisateurRepository.findById(idUtilisateur);
        Optional<ProjetEntity> optionalProjetEntity = projetRepository.findByIdProjet(idProjet);
        DroitProjetEntity droitProjet = droitProjetRepository.findDroitProjetByNomDroit(droit);

        // check si l utilisateur existe et si le projet existe
        if (optionalUtilisateurEntity.isPresent() && optionalProjetEntity.isPresent())
        {
            UtilisateurEntity utilisateurEntity = optionalUtilisateurEntity.get();
            ProjetEntity projetEntity = optionalProjetEntity.get();

            Optional<RoleProjetEntity> optionalRoleUtilisateur = projetEntity.getMembresDuProjet().stream()
                    .filter(m -> m.getUtilisateurParticipant().equals(utilisateurEntity))
                    .map(ParticipationEntity::getRoleDuParticipant).findFirst();

            // check si l'utilisateur participe bien au projet
            // utilisateur ne participe pas au projet
            return optionalRoleUtilisateur.map(roleProjetEntity -> roleProjetEntity.getDroitProjets().contains(droitProjet)).orElse(false);

        } return false ; //utilisateur ou projet n existe pas

    }

    private boolean verificationValidationTacheSpecialHistorique (List<HistoriqueTacheEntity> historiqueTache, List<TacheEntity> tacheSpecialEntityList,Long idEtape)
    {
        // transformer en liste de id filtrée par l id de l etape (dernière étape pour tache précédente et l'étape suivante pour taches enfants)
        // triée et sans doublons possible
        List<Long> historiqueList = historiqueTache.stream().filter(h -> h.getEtapeWorkflowTacheHistorique().getIdEtapeWorkflow().equals(idEtape))
                .map(m -> m.getTacheHistorique().getIdTache())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        // list d id des taches speciales (enfants ou precedentes) triée et sans doublons possible
        List<Long> tachesSpecialList = tacheSpecialEntityList.stream()
                .map(TacheEntity::getIdTache)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        // selectionner les elements communs des deux listes d id cad retirer les taches modifée gardée en historique
        // va produire null pointer si une des deux liste est null (ce qui est vérifié avec le Optional.isPresent())
        historiqueList.retainAll(tachesSpecialList);

        // check si tous les elements de la liste tachesSpecial se retrouvent dans la liste historique

        return tachesSpecialList.equals(historiqueList);
    }


}

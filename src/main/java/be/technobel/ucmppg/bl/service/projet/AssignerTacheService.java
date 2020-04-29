package be.technobel.ucmppg.bl.service.projet;

import be.technobel.ucmppg.Exception.ErrorServiceException;
import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.configuration.Constantes;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AssignerTacheService {

    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private DroitProjetRepository droitProjetRepository;
    @Autowired
    private HistoriqueTacheRepository historiqueTacheRepository;
    @Autowired
    private EtapeWorkflowRepository etapeWorkflowRepository;


    public ProjetDTO assignation (long idUtilisateurAssigne, long idUtilisateurAssignateur, long idTache) throws ErrorServiceException {

        Optional<TacheEntity> optionalTacheEntity = tacheRepository.findById(idTache);

        if (optionalTacheEntity.isPresent())
        {
            TacheEntity tacheEntity = optionalTacheEntity.get();
            boolean estDroitAssignationValide = true;
            boolean estDroitRemplacerUtilisateurValide = true;
            boolean estDroitPrendreTacheValide = true;
            Optional<Long> tacheParente = tacheRepository.getIdTacheParent(idTache);

            // Verifier si il s'agit d'une tache enfant et si oui chercher l idProjet de la tache parente
            Long idProjet = tacheParente.isPresent() ? projetRepository.getIdProjetEntityByTacheEnfant(idTache) : projetRepository.getIdProjetEntityByTacheParent(idTache) ;


            // on test si un utilisateur est deja en charge de la tache et si on a le droit de la remplacer
            if (tacheEntity.getUtilisateur_Tache() != null)
            {
                estDroitRemplacerUtilisateurValide = verificationDroitUtilisateurService(idUtilisateurAssignateur, Constantes.DROIT_SUPPRIMER_COLLABORATEUR_TACHE,idProjet);
            }

            // on verifie si un utilisateur prend une tache pour assigne a un autre utilisateur
            if (idUtilisateurAssignateur != idUtilisateurAssigne)
            {
                estDroitAssignationValide = verificationDroitUtilisateurService(idUtilisateurAssignateur,Constantes.DROIT_ASSIGNER_TACHE,idProjet);
                estDroitPrendreTacheValide = verificationDroitUtilisateurService(idUtilisateurAssigne,Constantes.DROIT_PRENDRE_TACHE,idProjet);
            } else {estDroitPrendreTacheValide = verificationDroitUtilisateurService(idUtilisateurAssignateur,Constantes.DROIT_PRENDRE_TACHE,idProjet);}

            // on verifie si toutes les conditions sont remplies
            if (estDroitAssignationValide && estDroitPrendreTacheValide && estDroitRemplacerUtilisateurValide)
            {
                Optional<UtilisateurEntity> optionalUtilisateurEntity = utilisateurRepository.findById(idUtilisateurAssigne);
                tacheEntity.setUtilisateur_Tache(optionalUtilisateurEntity.get()) ;
                tacheRepository.save(tacheEntity);

                // on crée un nouvel historique
                EtapeWorkflowEntity etapeWorkflowEntityActuelle = tacheParente.isPresent() ? etapeWorkflowRepository.getEtapeByIdTache(idTache): etapeWorkflowRepository.getEtapeByIdTacheEnfant(idTache) ;

                HistoriqueTacheEntity historiqueTacheEntity = new HistoriqueTacheEntity(tacheEntity,etapeWorkflowEntityActuelle,optionalUtilisateurEntity.get());
                historiqueTacheRepository.save(historiqueTacheEntity);

                return new ProjetDTO(projetRepository.findById(idProjet).get());

            } else
            {
                String errorMessage = null;
                if (!estDroitRemplacerUtilisateurValide){
                    errorMessage = "L'utilisateur ne possède pas le droit de remplacer l'utilisateur dejà assigné à la tache";
                } else if (!estDroitAssignationValide){
                    errorMessage = "L'utilisateur ne possède pas le droit d'assigner un utilisateur à une tache";
                } else errorMessage = "L'utilisateur ne possède pas le droit de prendre une tache";

                throw  new ErrorServiceException("Assigner utilisateur à une tache",errorMessage);
            }

        }  throw  new ErrorServiceException("Assigner utilisateur à une tache","La tache n'existe pas");

    }

    public ProjetDTO congedierUtilisateur (long idTache, long idUtilisateur) throws ErrorServiceException
    {
        Optional<TacheEntity> optionalTacheEntity = tacheRepository.findById(idTache);

        if (optionalTacheEntity.isPresent())
        {
            TacheEntity tacheEntity = optionalTacheEntity.get();

            if (tacheEntity.getUtilisateur_Tache() != null)
            {
                Optional<Long> tacheParente = tacheRepository.getIdTacheParent(idTache);

                // Verifier si il s'agit d'une tache enfant et si oui chercher l idProjet de la tache parente
                Long idProjet = tacheParente.isPresent() ? projetRepository.getIdProjetEntityByTacheEnfant(idTache) : projetRepository.getIdProjetEntityByTacheParent(idTache) ;

                boolean estDroitCongedierValide = verificationDroitUtilisateurService(idUtilisateur,Constantes.DROIT_SUPPRIMER_COLLABORATEUR_TACHE,idProjet);

                if (estDroitCongedierValide)
                {
                    //supprimer le user de la tache
                    tacheEntity.setUtilisateur_Tache(null);

                    // ajouter un historique
                    EtapeWorkflowEntity etapeWorkflowEntityActuelle = tacheParente.isPresent() ? etapeWorkflowRepository.getEtapeByIdTache(idTache): etapeWorkflowRepository.getEtapeByIdTacheEnfant(idTache) ;
                    HistoriqueTacheEntity historiqueTacheEntity = new HistoriqueTacheEntity(tacheEntity,etapeWorkflowEntityActuelle,null);
                    historiqueTacheRepository.save(historiqueTacheEntity);

                    return new ProjetDTO(projetRepository.findById(idProjet).get());

                } throw  new ErrorServiceException("Congédier un utilisateur d'une tache","L'utilisateur ne possède pas le droit de prendre une tache");

            } throw  new ErrorServiceException("Congédier un utilisateur d'une tache","La tache ne comprend pas d utilisateur assigné");

        } throw  new ErrorServiceException("Congédier un utilisateur d'une tache","La tache n'existe pas");
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
}

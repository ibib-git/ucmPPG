package be.technobel.ucmppg.bl.service.projet;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.configuration.Constantes;
import be.technobel.ucmppg.dal.entities.DroitProjetEntity;
import be.technobel.ucmppg.dal.entities.TacheEntity;
import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import be.technobel.ucmppg.dal.repositories.DroitProjetRepository;
import be.technobel.ucmppg.dal.repositories.ProjetRepository;
import be.technobel.ucmppg.dal.repositories.TacheRepository;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ValiderTacheService {

    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private DroitProjetRepository droitProjetRepository;
    @Autowired
    private ProjetRepository projetRepository;


    public ProjetDTO execute (long idUtilisateur, long idTache)
    {
        //Verifier que la tache existe et que les taches précédentes et enfants sont toutes validée
        //Verifier que la colonne actuelle n'est pas la dernière
        // Si les deux sont ok alors

        Optional<TacheEntity> tache = tacheRepository.findById(idTache);
        Optional<Long> tacheParente = tacheRepository.getIdTacheParent(idTache);

        // Verifier si il s'agit d'une tache enfant et si oui chercher l idProjet de la tache parente
        Long idProjet = tacheParente.isPresent() ? projetRepository.getIdProjetEntityByTacheEnfant(idTache) : projetRepository.getIdProjetEntityByTacheParent(idTache) ;

        //Verifier que l'utilisateur existe et a le droit
        if (verificationDroitUtilisateurService(idUtilisateur, Constantes.DROIT_VALIDER_TACHE,idProjet))
        {
            // Vérifier si il y a des taches précédentes et si elles sont bien validée pour l'étape
            Optional<List<TacheEntity>> tachePrecedenteEntityList = tacheRepository.getAllTachePrecedente(idTache);

            if (tachePrecedenteEntityList.isPresent())
            {

            }
            if (tacheParente.isPresent())
            {


            } else {
                // récupérer l'historique des taches enfant

            }

        }

        return null;
    }

    private boolean verificationDroitUtilisateurService (long idUtilisateur, String droit, long idProjet)
    {
        Optional<UtilisateurEntity> utilisateurEntity = utilisateurRepository.findById(idUtilisateur);
        DroitProjetEntity droitProjet = droitProjetRepository.findDroitProjetByNomDroit(droit);

        return utilisateurEntity.map(entity -> entity.getProjetsParticiperUtilisateur().stream()
                .filter(p -> p.getProjetParticipation().getIdProjet() == idProjet)
                .map(p -> {
                    return ((boolean) p.getRoleDuParticipant().getDroitProjets().contains(droitProjet));
                }).findFirst().orElse(false)).orElse(false);
    }
}

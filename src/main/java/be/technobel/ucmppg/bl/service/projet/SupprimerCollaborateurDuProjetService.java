package be.technobel.ucmppg.bl.service.projet;

import be.technobel.ucmppg.bl.dto.projet.collaborateur.SupprimerCollaborateurDTO;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.HistoriqueTacheRepository;
import be.technobel.ucmppg.dal.repositories.ProjetRepository;
import be.technobel.ucmppg.dal.repositories.TacheRepository;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupprimerCollaborateurDuProjetService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private HistoriqueTacheRepository historiqueTacheRepository;
    @Autowired
    private TacheRepository tacheRepository;

    public boolean execute(SupprimerCollaborateurDTO supprimerCollaborateurDTO){

        Optional<UtilisateurEntity> optionalUtilisateurEntity = utilisateurRepository.findByEmailUtilisateur(supprimerCollaborateurDTO.getMail());
        Optional<ProjetEntity> optionalProjetEntity = projetRepository.findByIdProjet(supprimerCollaborateurDTO.getIdProjet());

        if(optionalUtilisateurEntity.isPresent() & optionalProjetEntity.isPresent()){
            UtilisateurEntity utilisateur = optionalUtilisateurEntity.get();
            ProjetEntity projet = optionalProjetEntity.get();

            if(!utilisateur.getPseudoUtilisateur().equals(projet.getUtilisateurCreateur().getPseudoUtilisateur())) {

                for (ParticipationEntity participationUtilisateur : utilisateur.getProjetsParticiperUtilisateur()) {
                    if (participationUtilisateur.getUtilisateurParticipant().getPseudoUtilisateur().equals(utilisateur.getPseudoUtilisateur())) {
                        utilisateur.getProjetsParticiperUtilisateur().remove(participationUtilisateur);
                    }
                }
                for (ParticipationEntity participationProjet : projet.getMembresDuProjet()) {
                    if (participationProjet.getProjetParticipation().getIdProjet().equals(projet.getIdProjet())) {
                        projet.getMembresDuProjet().remove(participationProjet);
                    }
                }
                for (EtapeWorkflowEntity workflow : projet.getEtapeWorkflows()){
                    for (TacheEntity tache: workflow.getTaches()) {
                        if(tache.getUtilisateur_Tache().getPseudoUtilisateur().equals(utilisateur.getPseudoUtilisateur())){
                            HistoriqueTacheEntity historique = new HistoriqueTacheEntity(null,tache,workflow,utilisateur);
                            historiqueTacheRepository.save(historique);
                            tache.setUtilisateur_Tache(null);
                            tacheRepository.save(tache);
                        }
                    }

                }
                projetRepository.save(projet);
                utilisateurRepository.save(utilisateur);

                return true;
            }
        }
        return false;
    }
}

package be.technobel.ucmppg.bl.service.projet;

import be.technobel.ucmppg.bl.dto.projet.collaborateur.SupprimerCollaborateurDTO;
import be.technobel.ucmppg.dal.entities.ParticipationEntity;
import be.technobel.ucmppg.dal.entities.ProjetEntity;
import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import be.technobel.ucmppg.dal.repositories.ProjetRepository;
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

    public boolean execute(SupprimerCollaborateurDTO supprimerCollaborateurDTO){

        Optional<UtilisateurEntity> optionalUtilisateurEntity = utilisateurRepository.findById(supprimerCollaborateurDTO.getIdUtilisateur());
        Optional<ProjetEntity> optionalProjetEntity = projetRepository.findByIdProjet(supprimerCollaborateurDTO.getIdProjet());

        if(optionalUtilisateurEntity.isPresent() & optionalProjetEntity.isPresent()){
            UtilisateurEntity utilisateur = optionalUtilisateurEntity.get();
            ProjetEntity projet = optionalProjetEntity.get();

            for (ParticipationEntity participationUtilisateur: utilisateur.getProjetsParticiperUtilisateur()) {
                if(participationUtilisateur.getUtilisateurParticipant().getPseudoUtilisateur().equals(utilisateur.getPseudoUtilisateur())){
                    utilisateur.getProjetsParticiperUtilisateur().remove(participationUtilisateur);
                }
            }

            for (ParticipationEntity participationProjet: projet.getMembresDuProjet()) {
                if(participationProjet.getProjetParticipation().getIdProjet().equals(projet.getIdProjet())){
                    projet.getMembresDuProjet().remove(participationProjet);
                }
            }

            projetRepository.save(projet);
            utilisateurRepository.save(utilisateur);

            return true;
        }
        return false;
    }
}

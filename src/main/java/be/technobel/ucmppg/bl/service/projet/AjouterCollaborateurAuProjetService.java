package be.technobel.ucmppg.bl.service.projet;

import be.technobel.ucmppg.dal.entities.ParticipationEntity;
import be.technobel.ucmppg.dal.entities.ProjetEntity;
import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import be.technobel.ucmppg.dal.repositories.ProjetRepository;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AjouterCollaborateurAuProjetService {

    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public void execute(long idProjet,long idCollaborateur){
        Optional<ProjetEntity> projetOptionalEntity= projetRepository.findById(idProjet);
        Optional<UtilisateurEntity> utilisateurOptionalEntity=utilisateurRepository.findById(idCollaborateur);

        if(projetOptionalEntity.isPresent() && utilisateurOptionalEntity.isPresent()){
            ProjetEntity projetEntity=projetOptionalEntity.get();
            UtilisateurEntity utilisateurEntity=utilisateurOptionalEntity.get();

            projetEntity.getMembresDuProjet();
            ParticipationEntity participationEntity=new ParticipationEntity();
            participationEntity.setProjetParticipation(projetEntity);
            participationEntity.setUtilisateurParticipant(utilisateurEntity);

        }
    }
}

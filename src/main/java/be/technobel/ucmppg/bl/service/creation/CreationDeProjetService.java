package be.technobel.ucmppg.bl.service.creation;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.dal.entities.ProjetEntity;
import be.technobel.ucmppg.dal.entities.RoleProjetEntity;
import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import be.technobel.ucmppg.dal.repositories.ParticipationRepository;
import be.technobel.ucmppg.dal.repositories.ProjetRepository;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreationDeProjetService implements CreationDeProjetInterface {

    private CreationParDefautService creation;

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private ParticipationRepository participationRepository;

    @Override
    public ProjetDTO execute(String nom, String description, long idCreateur) {
        Optional<UtilisateurEntity> utilisateurOptionalEntity=utilisateurRepository.findById(idCreateur);
        if(utilisateurOptionalEntity.isPresent()){

            UtilisateurEntity utilisateurEntity=utilisateurOptionalEntity.get();

            ProjetEntity projetEntity=new ProjetEntity();
            projetEntity.setNomDeProjet(nom);
            projetEntity.setDescriptionDeProjet(description);
            projetEntity.setUtilisateurCreateur(utilisateurEntity);

            RoleProjetEntity roleProjetEntity=new RoleProjetEntity();
            roleProjetEntity.setNomDeRole("admin");


        }

        return null;
    }
}

package be.technobel.ucmppg.bl.service.creation;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.dal.entities.DroitProjetEntity;
import be.technobel.ucmppg.dal.entities.ProjetEntity;
import be.technobel.ucmppg.dal.entities.RoleProjetEntity;
import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import be.technobel.ucmppg.dal.repositories.DroitProjetRepository;
import be.technobel.ucmppg.dal.repositories.ParticipationRepository;
import be.technobel.ucmppg.dal.repositories.ProjetRepository;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CreationDeProjetService implements CreationDeProjetInterface {

//    private CreationParDefautService creation;

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private DroitProjetRepository droitProjetRepository;
    @Override
    public ProjetDTO execute(String nom, String description, long idCreateur) {
        Optional<UtilisateurEntity> utilisateurOptionalEntity=utilisateurRepository.findById(idCreateur);

        ProjetDTO projetDTO=null;
        if(utilisateurOptionalEntity.isPresent()){

            UtilisateurEntity utilisateurEntity=utilisateurOptionalEntity.get();

            ProjetEntity projetEntity=new ProjetEntity();
            projetEntity.setNomDeProjet(nom);
            projetEntity.setDescriptionDeProjet(description);
            projetEntity.setUtilisateurCreateur(utilisateurEntity);

            RoleProjetEntity roleProjetEntity=new RoleProjetEntity();
            roleProjetEntity.setNomDeRole("administrateur");
            Set<DroitProjetEntity> droitProjetEntities=new HashSet<>();
            droitProjetRepository.findAll().forEach(
                    droitProjetEntities::add
            );
            roleProjetEntity.setDroitProjets(droitProjetEntities);


            projetDTO=new ProjetDTO(projetEntity);

        }
        return projetDTO;
    }
}

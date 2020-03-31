package be.technobel.ucmppg.bl.service.projet;

import be.technobel.ucmppg.dal.entities.ParticipationEntity;
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
public class AjouterCollaborateurAuProjetService {

    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ParticipationRepository participationRepository;

    public boolean execute(long idProjet,String emailCollaborateur){
        System.out.println(idProjet);
        Optional<ProjetEntity> projetOptionalEntity= projetRepository.findById(idProjet);
        System.out.println(projetOptionalEntity.get());
        System.out.println(emailCollaborateur);
        Optional<UtilisateurEntity> utilisateurOptionalEntity=utilisateurRepository.findByEmailUtilisateur(emailCollaborateur);
        System.out.println(utilisateurOptionalEntity.get());
        if(projetOptionalEntity.isPresent() && utilisateurOptionalEntity.isPresent()){
            ProjetEntity projetEntity=projetOptionalEntity.get();
            UtilisateurEntity utilisateurEntity=utilisateurOptionalEntity.get();

            projetEntity.getMembresDuProjet();

            //recherche du role membre
            RoleProjetEntity roleMembre=projetEntity.getRolesProjet()
                    //on cherche le role membre et on le stocke
                    .stream()
                        .filter(
                                role->role.getNomDeRole().equals("membre")
                        ).findFirst()
                    //si ce role n'existe pas on stocke le premier role de la liste à défaut
                    .orElse(
                            projetEntity.getRolesProjet()
                                    .stream()
                                    .findFirst()
                                    .get()
                    );

            ParticipationEntity participationEntity=new ParticipationEntity();
            participationEntity.setProjetParticipation(projetEntity);
            participationEntity.setUtilisateurParticipant(utilisateurEntity);
            participationEntity.setRoleDuParticipant(roleMembre);

            projetEntity.getMembresDuProjet().add(participationEntity);
            utilisateurEntity.getProjetsParticiperUtilisateur().add(participationEntity);

            participationRepository.save(participationEntity);
            projetRepository.save(projetEntity);
            utilisateurRepository.save(utilisateurEntity);

            return true;
        }
        return false;
    }
}

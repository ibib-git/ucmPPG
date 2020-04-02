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

import javax.management.relation.Role;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AjouterCollaborateurAuProjetService {

    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ParticipationRepository participationRepository;

    public boolean execute(long idProjet,String emailCollaborateur){
        Optional<ProjetEntity> projetOptionalEntity= projetRepository.findById(idProjet);
        Optional<UtilisateurEntity> utilisateurOptionalEntity=utilisateurRepository.findByEmailUtilisateur(emailCollaborateur);
        if(projetOptionalEntity.isPresent() && utilisateurOptionalEntity.isPresent()){
            ProjetEntity projetEntity=projetOptionalEntity.get();
            UtilisateurEntity utilisateurEntity=utilisateurOptionalEntity.get();

            //recherche du role membre
            RoleProjetEntity roleProjetEntity=null;
            Iterator<RoleProjetEntity> iterateurRole=projetEntity.getRolesProjet().iterator();
            while(roleProjetEntity==null && iterateurRole.hasNext()){
                RoleProjetEntity next=iterateurRole.next();
                if(next.getNomDeRole().equals("membre"))roleProjetEntity=next;
            };
            if(roleProjetEntity==null){
                if(projetEntity.getRolesProjet().size()>0){
                    roleProjetEntity=projetEntity.getRolesProjet().iterator().next();
                }
            }

            ParticipationEntity participationEntity=new ParticipationEntity();
            participationEntity.setProjetParticipation(projetEntity);
            participationEntity.setUtilisateurParticipant(utilisateurEntity);
            participationEntity.setRoleDuParticipant(roleProjetEntity);

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

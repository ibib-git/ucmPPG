package be.technobel.ucmppg.bl.service.projet;

import be.technobel.ucmppg.bl.dto.projet.collaborateur.SupprimerCollaborateurDTO;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSOutput;

import java.util.*;

@Service
public class SupprimerCollaborateurDuProjetService {

    @Autowired
    private HistoriqueTacheRepository historiqueTacheRepository;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private EtapeWorkflowRepository etapeWorkflowRepository;

    public boolean execute(SupprimerCollaborateurDTO supprimerCollaborateurDTO){
        Optional<ProjetEntity> optionalProjetEntity = projetRepository.findByIdProjet(supprimerCollaborateurDTO.getIdProjet());
        Optional<UtilisateurEntity> optionalUtilisateurEntity = utilisateurRepository.findByEmailUtilisateur(supprimerCollaborateurDTO.getMail());
        if(optionalUtilisateurEntity.isPresent()&& optionalProjetEntity.isPresent()) {
            UtilisateurEntity utilisateur = optionalUtilisateurEntity.get();
            ProjetEntity projet = optionalProjetEntity.get();
            Optional<ParticipationEntity> optionalParticipationEntity = participationRepository.findByParticipationOfProjet(utilisateur.getIdUtilisateur(),projet.getIdProjet());
            if(optionalParticipationEntity.isPresent()){
                Optional<List<TacheEntity>> optionalTacheEntities = tacheRepository.findByProjetAndUtilisateur(projet.getIdProjet(),utilisateur.getIdUtilisateur());
                if(optionalTacheEntities.isPresent()) {
                    for (TacheEntity tacheEntity : optionalTacheEntities.get()) {
                        tacheEntity.setUtilisateur_Tache(null);
                        HistoriqueTacheEntity historiqueTacheEntity = new HistoriqueTacheEntity();
                        historiqueTacheEntity.setEtapeWorkflowTacheHistorique(recuperationEtapeWorkflow(tacheEntity));
                        historiqueTacheEntity.setTacheHistorique(tacheEntity);
                        historiqueTacheEntity.setUtilisateur_Tache_historique(utilisateur);
                        historiqueTacheRepository.save(historiqueTacheEntity);
                        tacheRepository.save(tacheEntity);
                    }
                }
                ParticipationEntity participationEntity = optionalParticipationEntity.get();
                participationEntity.setActif(false);
                participationRepository.save(participationEntity);
                projetRepository.save(projet);
                utilisateurRepository.save(utilisateur);
            }
            return true;
        }
        return false;
    }

    private EtapeWorkflowEntity recuperationEtapeWorkflow(TacheEntity tacheEntity){

        return etapeWorkflowRepository.findByTacheEntity(tacheEntity.getIdTache()).get();

    }

}

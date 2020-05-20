package be.technobel.ucmppg.bl.service.tache;

import be.technobel.ucmppg.bl.dto.projet.taches.TacheCreationDTO;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TacheAjouterService {

    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private EtapeWorkflowRepository etapeWorkflowRepository;
    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private HistoriqueTacheRepository historiqueTacheRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public boolean creationTache(Long idWorkflow, Long idProjet, Long idTache, TacheCreationDTO tacheCreationDTO) {
        // Recuperation des Etape 1 et projet
        Optional<ProjetEntity> optionalProjetEntity = projetRepository.findByIdProjet(idProjet);
        Optional<EtapeWorkflowEntity> optionalEtapeWorkflowEntity = etapeWorkflowRepository.findById(idWorkflow);

        // Vérification de Optional
        if (optionalEtapeWorkflowEntity.isPresent() && optionalProjetEntity.isPresent()) {

            // Initialisation des tacheEntitys
            TacheEntity tacheEntity = remplissageDesDonneesEntrees(tacheCreationDTO.getNom(), tacheCreationDTO.getPriorite(),
                        tacheCreationDTO.getDescription(), tacheCreationDTO.getUnite(), tacheCreationDTO.getEstimation());
            // enregistrement
            tacheRepository.save(tacheEntity);

            // Get des etape et projet
            EtapeWorkflowEntity etapeWorkflowEntity = optionalEtapeWorkflowEntity.get();
            ProjetEntity projetEntity = optionalProjetEntity.get();

            // Initialisation de historique et sauvegarde
            HistoriqueTacheEntity historiqueTacheEntity = new HistoriqueTacheEntity(tacheEntity, etapeWorkflowEntity, null);
            historiqueTacheRepository.save(historiqueTacheEntity);

            // Tache Parents
            if(idTache != null){
                // Optional et vérification si ça existe
                Optional<TacheEntity> optionalTacheEntity = tacheRepository.findById(idTache);
                if(optionalTacheEntity.isPresent()){
                    // Ajout de NewTache et sauvegarde du parent
                    TacheEntity tacheEntityParent = optionalTacheEntity.get();
                    tacheEntityParent.getTachesEnfants().add(tacheEntity);
                    tacheRepository.save(tacheEntityParent);
                }else{
                    return false;
                }
            }else{
                // Sans tacheParent et etape recupere la tache
                etapeWorkflowEntity.getTaches().add(tacheEntity);
                etapeWorkflowRepository.save(etapeWorkflowEntity);
            }
            // sauvegarde Final
            tacheRepository.save(tacheEntity);
            projetRepository.save(projetEntity);
            return true;
        }
            return false;
    }

    public TacheEntity remplissageDesDonneesEntrees (String nom, String priorite, String description, String unite, int estimation){

        //Création de Tache avec Nom, Utilisateur Null, Pas de tache enfant, pas de tache Précédent, Pas de Historique
        TacheEntity tacheEntity = new TacheEntity();
        tacheEntity.setUtilisateur_Tache(null);
        tacheEntity.setNomTache(nom);

        // Description de tache si pas null
        if (description != null) {
            tacheEntity.setDescriptionTache(description);
        }

        // Unite de temps qui est une Enum
        if (unite != null) {
            for (UniteDeTempsEnum uniteEnum : UniteDeTempsEnum.values()) {
                if (uniteEnum.name().equals(unite)) {
                    tacheEntity.setUniteDeTemps_tache(uniteEnum);
                }
            }
        }

        // Estimation de temps si elle n'est pas null;
        if (estimation != 0) {
            tacheEntity.setEstimationDeTemps_Tache(estimation);
        }

        // Priorite de la tache si elle est pas null
        if (priorite != null) {
            for (Priorite p : Priorite.values()) {
                if (p.name().equals(priorite)) {
                    tacheEntity.setPriorite(p);
                }
            }
        }
        return tacheEntity;
    }
}

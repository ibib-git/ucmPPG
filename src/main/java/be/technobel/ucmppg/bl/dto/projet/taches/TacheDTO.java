package be.technobel.ucmppg.bl.dto.projet.taches;

import be.technobel.ucmppg.bl.dto.HistoriqueDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;
import be.technobel.ucmppg.dal.entities.Priorite;
import be.technobel.ucmppg.dal.entities.TacheEntity;
import be.technobel.ucmppg.dal.entities.UniteDeTempsEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TacheDTO {

    private Long id;
    private String nom;
    private String description;
    private List<TacheDTO> tacheEnfants=new ArrayList<>();
    private List<TacheDTO> tachesPrecedentes=new ArrayList<>();
    private Integer estimationTemps;
    private UniteDeTempsEnum uniteDeTemps;
    private List<HistoriqueDTO> historique=new ArrayList<>();
    private UtilisateurDetailsDTO utilisateurAffecte;
    private Priorite priorite;

    public TacheDTO(TacheEntity tacheEntity) {
        this.id=tacheEntity.getIdTache();
        this.nom =tacheEntity.getNomTache();
        this.description=tacheEntity.getDescriptionTache();
        this.tacheEnfants=tacheEntity.getTachesEnfants().stream().map(TacheDTO::new).collect(Collectors.toList());
        this.tachesPrecedentes=tacheEntity.getTachesPrecedentes().stream().map(TacheDTO::new).collect(Collectors.toList());
        this.estimationTemps =tacheEntity.getEstimationDeTemps_Tache();
        this.uniteDeTemps =tacheEntity.getUniteDeTemps_tache();
        //todo : ajouter la gestion des historiques
        if(tacheEntity.getUtilisateur_Tache() != null){
            this.utilisateurAffecte=new UtilisateurDetailsDTO(tacheEntity.getUtilisateur_Tache());
        }else{
            this.utilisateurAffecte = null;
        }
        this.historique = tacheEntity.getHistoriqueTaches().stream().map(HistoriqueDTO::new).collect(Collectors.toList());
        this.priorite = tacheEntity.getPriorite();
        this.utilisateurAffecte= tacheEntity.getUtilisateur_Tache() == null ? null : new UtilisateurDetailsDTO(tacheEntity.getUtilisateur_Tache());
        this.historique = tacheEntity.getHistoriqueTaches().stream()
                .map(
                        HistoriqueDTO::new
                ).collect(Collectors.toList());
    }
}

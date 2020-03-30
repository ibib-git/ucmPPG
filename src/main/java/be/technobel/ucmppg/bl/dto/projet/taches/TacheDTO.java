package be.technobel.ucmppg.bl.dto.projet.taches;

import be.technobel.ucmppg.bl.dto.HistoriqueDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;
import be.technobel.ucmppg.dal.entities.TacheEntity;
import be.technobel.ucmppg.dal.entities.UniteDeTempsEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TacheDTO {

    private long id;
    private String nomTache;
    private String description;
    private List<Long> tacheEnfants=new ArrayList<>();
    private List<Long> tachesPrecedentes=new ArrayList<>();
    private Integer estimationTempsTache; //TODO Faut faire attention Tache int
    private UniteDeTempsEnum uniteDeTempsEnum;
    private List<HistoriqueDTO> historique=new ArrayList<>();
    private UtilisateurDetailsDTO utilisateurAffecte;

    public TacheDTO(TacheEntity tacheEntity) {
        this.id=tacheEntity.getIdTache();
        this.nomTache=tacheEntity.getNomTache();
        this.description=tacheEntity.getDescriptionTache();
        this.tacheEnfants=tacheEntity.getTachesEnfants().stream()
                .map(
                        TacheEntity::getIdTache
                ).collect(Collectors.toList());
        this.tachesPrecedentes=tacheEntity.getTachesPrecedentes().stream()
                .map(
                        TacheEntity::getIdTache
                ).collect(Collectors.toList());
        this.estimationTempsTache=tacheEntity.getEstimationDeTemps_Tache();
        this.uniteDeTempsEnum=tacheEntity.getUniteDeTemps_tache();
        //todo : ajouter la gestion des historiques
        this.utilisateurAffecte=new UtilisateurDetailsDTO(tacheEntity.getUtilisateur_Tache());
    }
}

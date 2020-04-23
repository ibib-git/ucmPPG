package be.technobel.ucmppg.bl.dto.projet.taches;

import be.technobel.ucmppg.bl.dto.HistoriqueDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;
import be.technobel.ucmppg.dal.entities.TacheEntity;
import be.technobel.ucmppg.dal.entities.UniteDeTempsEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TacheDTO {

    private long id;
    private String nom;
    private String description;
    private List<TacheDTO> tacheEnfants=new ArrayList<>();
    private List<TacheDTO> tachesPrecedentes=new ArrayList<>();
    private Integer estimationTemps;
    private UniteDeTempsEnum uniteDeTemps;
    private List<HistoriqueDTO> historique=new ArrayList<>();
    private UtilisateurDetailsDTO utilisateurAffecte;

    public TacheDTO(TacheEntity tacheEntity) {
        this.id=tacheEntity.getIdTache();
        this.nom =tacheEntity.getNomTache();
        this.description=tacheEntity.getDescriptionTache();
        this.tacheEnfants=tacheEntity.getTachesEnfants().stream()
                .map(
                        TacheDTO::new
                ).collect(Collectors.toList());
        this.tachesPrecedentes=tacheEntity.getTachesPrecedentes().stream()
                .map(
                        TacheDTO::new
                ).collect(Collectors.toList());
        this.estimationTemps =tacheEntity.getEstimationDeTemps_Tache();
        this.uniteDeTemps =tacheEntity.getUniteDeTemps_tache();
        //todo : ajouter la gestion des historiques
        this.utilisateurAffecte=new UtilisateurDetailsDTO(tacheEntity.getUtilisateur_Tache());
        this.historique = tacheEntity.getHistoriqueTaches().stream()
                .map(
                        HistoriqueDTO::new
                ).collect(Collectors.toList());
        System.out.println(this.historique);
        System.out.println(tacheEntity.getHistoriqueTaches());

    }
}

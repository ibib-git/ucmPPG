package be.technobel.ucmppg.API_Projet.DAO;

import be.technobel.ucmppg.BL.Models.DroitBusiness;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DroitDAO {

    private String nom;

    public DroitDAO(DroitBusiness droit) {
        this.nom = droit.getNom_Droit_Projet();
    }
}

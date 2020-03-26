package be.technobel.ucmppg.API_Projet.DTO;

import be.technobel.ucmppg.BL.Models.ProjetBusiness;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjetDTO {

    private ProjetBusiness projet_par_defaut;
    private List<String> email_utilisateur;

    public ProjetBusiness getProjet_par_defaut() {
        return projet_par_defaut;
    }

    public void setProjet_par_defaut(ProjetBusiness projet_par_defaut) {
        this.projet_par_defaut = projet_par_defaut;
    }

    public List<String> getEmail_utilisateur() {
        return email_utilisateur;
    }
}

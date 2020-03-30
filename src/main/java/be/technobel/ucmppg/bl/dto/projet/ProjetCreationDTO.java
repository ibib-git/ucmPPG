package be.technobel.ucmppg.bl.dto.projet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjetCreationDTO {

    private String nom;
    private String description;


    //private ProjetDTO projetParDefaut;
    //private List<String> emailUtilisateur;

//    public ProjetDTO getProjetParDefaut() {
//        return projetParDefaut;
//    }
//
//    public void setProjetParDefaut(ProjetDTO projetParDefaut) {
//        this.projetParDefaut = projetParDefaut;
//    }
//
//    public List<String> getEmailUtilisateur() {
//        return emailUtilisateur;
//    }
}

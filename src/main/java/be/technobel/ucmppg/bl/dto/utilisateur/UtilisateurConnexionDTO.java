package be.technobel.ucmppg.bl.dto.utilisateur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurConnexionDTO {

    private String mail;
    private String motDePasse;
}

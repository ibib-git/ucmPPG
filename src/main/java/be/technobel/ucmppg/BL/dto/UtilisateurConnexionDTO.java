package be.technobel.ucmppg.BL.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurConnexionDTO {

    private String email;
    private String password;
}

package be.technobel.ucmppg.bl.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UtilisateurDTO {

    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String pseudo;
    private String telephone;
    private String infoSuppl;
    private String urlPhoto;
    private List<ParticipationDTO> participation;

}

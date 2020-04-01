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

}

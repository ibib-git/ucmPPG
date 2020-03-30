package be.technobel.ucmppg.bl.service.creation;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;

public interface CreationDeProjetInterface {

    ProjetDTO execute(String nom,String description,long idCreateur);

}

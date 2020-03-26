package be.technobel.ucmppg.BL.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Historique_Tache_BL {

    private Utilisateur_BL utilisateur_Tache;
    private Etape_Worflow_BL etape_worflow_Tache;
    private Tache_BL tache_historique;

}

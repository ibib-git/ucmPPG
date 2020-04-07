package be.technobel.ucmppg;

import be.technobel.ucmppg.bl.service.creation.CreationDeProjetService;
import be.technobel.ucmppg.bl.service.projet.AjouterCollaborateurAuProjetService;
import be.technobel.ucmppg.configuration.Constantes;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.ProjetRepository;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;

@SpringBootTest
public class ChangerOrdreEtapeWorkflowTest {

    private ProjetEntity projetEntity;
    private UtilisateurEntity utilisateurCreateurEntity;
    private UtilisateurEntity utilisateurMembreEntity;
    private RoleProjetEntity roleEmpereurEntity;
    private RoleProjetEntity roleStormtrooperEntity;
    private RoleProjetEntity roleSithEntity;
    private DroitProjetEntity droitChangerOrdeEtape;

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private AjouterCollaborateurAuProjetService ajouterCollaborateurAuProjetService;
    @Autowired
    private CreationDeProjetService creationDeProjetService;

    @Etantdonné("un droit de changer l'ordre de 2 étapes workflow d'un projet")
    public void creerUnDroit ()
    {
        this.droitChangerOrdeEtape = new DroitProjetEntity();
        this.droitChangerOrdeEtape.setNomDroit(Constantes.DROIT_CHANGER_ORDRE_ETAPE);
    }

    @Et("un utilisateur createur de projet {utilisateur}")
    public void creerUtilisateurCreateurProjet(UtilisateurEntity utilisateur) {
        this.utilisateurCreateurEntity = utilisateur;
    }

    @Et("un projet {string} créé par ce dernier dont {string} avec un role {string} et un role {string} possédant le droit ainsi qu'un role {string} ne le possédant pas")
    public void creerProjet(String nom, String description, String roleEmpereur, String roleSith, String roleStormtrooper){

        //region definition du projet
        this.projetEntity=new ProjetEntity();
        this.projetEntity.setNomDeProjet(nom);
        this.projetEntity.setDescriptionDeProjet(description);
        this.projetEntity.setUtilisateurCreateur(this.utilisateurCreateurEntity);
        // endregion

        // region definition des roles du projet
            //region droits
        DroitProjetEntity droitTaches = new DroitProjetEntity(null, Constantes.DROIT_PRENDRE_TACHE);
        DroitProjetEntity droitCollaborateurs = new DroitProjetEntity(null, Constantes.DROIT_INVITER_COLLABORATEURS);
            // endregion
        // region role Empereur
        this.roleEmpereurEntity=new RoleProjetEntity();
        this.roleEmpereurEntity.setNomDeRole(roleEmpereur);
        Set<DroitProjetEntity> droitEmpereurEntities=new HashSet<>();
        droitEmpereurEntities.add(droitTaches);
        droitEmpereurEntities.add(droitCollaborateurs);
        droitEmpereurEntities.add(this.droitChangerOrdeEtape);
        this.roleEmpereurEntity.setDroitProjets(droitEmpereurEntities);
        // endregion empereur

        // region role sith
        this.roleSithEntity=new RoleProjetEntity();
        this.roleSithEntity.setNomDeRole(roleSith);
        Set<DroitProjetEntity> droitSithEntities=new HashSet<>();
        droitEmpereurEntities.add(this.droitChangerOrdeEtape);
        droitSithEntities.add(droitTaches);
        this.roleSithEntity.setDroitProjets(droitSithEntities);
        // endregion

        // region role soldat
        this.roleStormtrooperEntity =new RoleProjetEntity();
        this.roleStormtrooperEntity.setNomDeRole(roleStormtrooper);
        Set<DroitProjetEntity> droitStormtrooperEntities=new HashSet<>();
        droitStormtrooperEntities.add(droitTaches);
        this.roleStormtrooperEntity.setDroitProjets(droitStormtrooperEntities);
        // endregion

        Set<RoleProjetEntity> roleProjetEntities=new HashSet<>();
        roleProjetEntities.add(this.roleEmpereurEntity);
        roleProjetEntities.add(this.roleSithEntity);
        roleProjetEntities.add(this.roleStormtrooperEntity);
        this.projetEntity.setRolesProjet(roleProjetEntities);
        // endregion

        // region participation
        ParticipationEntity participationEmpereurEntity = new ParticipationEntity();
        participationEmpereurEntity.setUtilisateurParticipant(this.utilisateurCreateurEntity);
        participationEmpereurEntity.setRoleDuParticipant(this.roleEmpereurEntity);
        participationEmpereurEntity.setProjetParticipation(this.projetEntity);

        this.projetEntity.getMembresDuProjet().add(participationEmpereurEntity);
        this.utilisateurCreateurEntity.getProjetsParticiperUtilisateur().add(participationEmpereurEntity);
        // endregion
    }

    @Etantdonné("un utilisateur membre {utilisateur}")
    public void creerUtilisateurMembreProjet(UtilisateurEntity utilisateur) {
        this.utilisateurMembreEntity = utilisateur;
    }

    @Et("cet utilisateur participe au projet avec le role {string}")
    public void ajouterUtilisateurAvecRole(String role)
    {
        RoleProjetEntity roleAjoute = this.projetEntity.getRolesProjet().stream()
                .filter(r -> r.getNomDeRole().equals(role)).findFirst().orElse(null);

        ParticipationEntity participationMembreEntity = new ParticipationEntity();
        participationMembreEntity.setUtilisateurParticipant(this.utilisateurMembreEntity);
        participationMembreEntity.setRoleDuParticipant(roleAjoute);
        participationMembreEntity.setProjetParticipation(this.projetEntity);

        this.projetEntity.getMembresDuProjet().add(participationMembreEntity);

    }

    @Quand("l'utilisateur veut changer l'ordre de 2 étapes")
    public void changerOrdre ()
    {

       boolean result = changerOrdreEtapeService(this.projetEntity,this.utilisateurMembreEntity);

    }

    public boolean changerOrdreEtapeService(ProjetEntity projetEntity,UtilisateurEntity utilisateurEntity)
    {
        projetEntity = this.projetEntity;
        return false;
    }
}

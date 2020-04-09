package be.technobel.ucmppg.cucumber;

import be.technobel.ucmppg.bl.service.creation.CreationDeProjetService;
import be.technobel.ucmppg.bl.service.projet.AjouterCollaborateurAuProjetService;
import be.technobel.ucmppg.configuration.Constantes;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.ProjetRepository;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ChangerOrdreEtapeWorkflowTest {

    private ProjetEntity projetEntity;
    private UtilisateurEntity utilisateurCreateurEntity;
    private UtilisateurEntity utilisateurMembreEntity;
    private RoleProjetEntity roleEmpereurEntity;
    private RoleProjetEntity roleStormtrooperEntity;
    private RoleProjetEntity roleSithEntity;
    private DroitProjetEntity droitChangerOrdeEtape;
    private Set<EtapeWorkflowEntity> etapeWorkflowEntitySet;
    private boolean result;


    @Etantdonné("un droit de changer l'ordre d'une étape workflow d'un projet")
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
        droitSithEntities.add(this.droitChangerOrdeEtape);
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

    @Et("avec une liste d'étapes workflow avec leurs numero d'ordre :")
    public void creerListeEtape (List<List<String>> etapeWorkflowEntityList)
    {
        this.etapeWorkflowEntitySet = new HashSet<>();

        etapeWorkflowEntityList.forEach(l -> {
            EtapeWorkflowEntity etape = new EtapeWorkflowEntity();
            etape.setNomEtapeWorkflow(l.get(0));
            etape.setNumOrdreEtapeWorkflow(Integer.parseInt(l.get(1)));
            this.etapeWorkflowEntitySet.add(etape);
        });
        this.projetEntity.setEtapeWorkflows(this.etapeWorkflowEntitySet);
    }

    // region Scénario: Un membre de projet qui a le droit va changer l'ordre d'une colonne

    @Etantdonné("un nouvel utilisateur {utilisateur}")
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

    @Quand("l'utilisateur veut changer l'ordre de l'étape {string} en ordre {int}")
    public void changerOrdre (String etape,int newOrdre)
    {
       this.result = changerOrdreEtapeService(this.projetEntity,this.utilisateurMembreEntity,etape,newOrdre);
    }

    @Alors("l'ordre des étapes devient {string} = {int}, {string} = {int}, {string} = {int}, {string} = {int} de plus le service renvoie {string}")
    public void ordreEstchange(String etape1,int ordreEtape1,String etape2,int ordreEtape2,String etape3,int ordreEtape3,String etape4,int ordreEtape4,String resultat )
    {
       EtapeWorkflowEntity etape1Entity = this.projetEntity.getEtapeWorkflows().stream()
               .filter(e -> e.getNomEtapeWorkflow().equals(etape1)).findFirst().orElse(null);
        EtapeWorkflowEntity etape2Entity = this.projetEntity.getEtapeWorkflows().stream()
                .filter(e -> e.getNomEtapeWorkflow().equals(etape2)).findFirst().orElse(null);
        EtapeWorkflowEntity etape3Entity = this.projetEntity.getEtapeWorkflows().stream()
                .filter(e -> e.getNomEtapeWorkflow().equals(etape3)).findFirst().orElse(null);
        EtapeWorkflowEntity etape4Entity = this.projetEntity.getEtapeWorkflows().stream()
                .filter(e -> e.getNomEtapeWorkflow().equals(etape4)).findFirst().orElse(null);


        assert etape1Entity != null;
        assert etape2Entity != null;
        assert etape3Entity != null;
        assert etape4Entity != null;
        Assert.assertEquals((int) etape1Entity.getNumOrdreEtapeWorkflow(), ordreEtape1);
        Assert.assertEquals((int) etape2Entity.getNumOrdreEtapeWorkflow(), ordreEtape2);
        Assert.assertEquals((int) etape3Entity.getNumOrdreEtapeWorkflow(), ordreEtape3);
        Assert.assertEquals((int) etape4Entity.getNumOrdreEtapeWorkflow(), ordreEtape4);
        Assert.assertEquals(resultat,String.valueOf(this.result));
    }

    // endregion

    public boolean changerOrdreEtapeService(ProjetEntity projetEntity,UtilisateurEntity utilisateurEntity,String nomEtape,int nvOrdre)
    {

        //1 trouver la colonne puis lui changer son numero ordre
        //2 regarder si une autre colonne possède ce nouveau numero
        //3 si oui alors faut incrémenter son numero ainsi que celle d'apres
        //4 si non alors finit
        //5 tester si utilisateur a le droit
        Set<EtapeWorkflowEntity> etapeWorkflowEntitySet = projetEntity.getEtapeWorkflows();
        EtapeWorkflowEntity etapeInput = etapeWorkflowEntitySet.stream().filter(e -> e.getNomEtapeWorkflow().equals(nomEtape)).findFirst().orElse(null);

        RoleProjetEntity roleUtilisateur = projetEntity.getMembresDuProjet().stream()
                .filter(m -> m.getUtilisateurParticipant().equals(utilisateurEntity))
                .map(ParticipationEntity::getRoleDuParticipant).findFirst().orElse(null);

        assert roleUtilisateur != null;
        Optional<DroitProjetEntity> droitUtilisateur = roleUtilisateur.getDroitProjets().stream()
                .filter(d -> d.getNomDroit().equals(Constantes.DROIT_CHANGER_ORDRE_ETAPE))
                .findFirst();

        if (etapeInput !=  null && droitUtilisateur.isPresent())
        {

            if (nvOrdre < etapeInput.getNumOrdreEtapeWorkflow())
            {
                etapeWorkflowEntitySet.stream()
                        .filter(e -> e.getNumOrdreEtapeWorkflow() >= nvOrdre && e.getNumOrdreEtapeWorkflow() < etapeInput.getNumOrdreEtapeWorkflow())
                        .forEach(e -> e.setNumOrdreEtapeWorkflow(e.getNumOrdreEtapeWorkflow()+1));
            } else {
                etapeWorkflowEntitySet.stream()
                        .filter(e -> e.getNumOrdreEtapeWorkflow() <= nvOrdre && e.getNumOrdreEtapeWorkflow() > etapeInput.getNumOrdreEtapeWorkflow())
                        .forEach(e -> e.setNumOrdreEtapeWorkflow(e.getNumOrdreEtapeWorkflow()-1));
            }

            etapeWorkflowEntitySet.stream()
                    .filter(e -> e.equals(etapeInput))
                    .forEach(e -> e.setNumOrdreEtapeWorkflow(nvOrdre));

            projetEntity.setEtapeWorkflows(etapeWorkflowEntitySet);
            return true;

        } else  return false;

    }
}

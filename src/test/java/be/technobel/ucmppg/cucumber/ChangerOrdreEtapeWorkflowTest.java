package be.technobel.ucmppg.cucumber;

import be.technobel.ucmppg.bl.service.creation.CreationDeProjetService;
import be.technobel.ucmppg.bl.service.projet.AjouterCollaborateurAuProjetService;
import be.technobel.ucmppg.bl.service.projet.ChangerOrdreEtapeService;
import be.technobel.ucmppg.configuration.Constantes;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.*;
import io.cucumber.java.After;
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
    private ParticipationEntity participationEntity,participationMembre;
    private DroitProjetEntity droitChangerOrdeEtape, droitTache, droitCollaborateur;
    private Set<EtapeWorkflowEntity> etapeWorkflowEntitySet;
    private boolean result;
    @Autowired
    ChangerOrdreEtapeService changerOrdreEtapeService;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    RoleProjetRepository roleProjetRepository;
    @Autowired
    DroitProjetRepository droitProjetRepository;
    @Autowired
    ParticipationRepository participationRepository;
    @Autowired
    EtapeWorkflowRepository etapeWorkflowRepository;


    @Etantdonné("un droit de changer l'ordre d'une étape workflow d'un projet")
    public void chercherUnDroit ()
    {
        this.droitChangerOrdeEtape = this.droitProjetRepository.findDroitProjetByNomDroit(Constantes.DROIT_CHANGER_ORDRE_ETAPE);
    }

    @Et("un utilisateur createur de projet {utilisateur}")
    public void creerUtilisateurCreateurProjet(UtilisateurEntity utilisateur) {
        this.utilisateurCreateurEntity = this.utilisateurRepository.save(utilisateur);
    }

    @Et("un projet {string} créé par ce dernier dont {string} avec un role {string} et un role {string} possédant le droit ainsi qu'un role {string} ne le possédant pas")
    public void creerProjet(String nom, String description, String roleEmpereur, String roleSith, String roleStormtrooper){

        //region definition du projet
        ProjetEntity projet=new ProjetEntity();
        projet.setNomDeProjet(nom);
        projet.setDescriptionDeProjet(description);
        projet.setUtilisateurCreateur(this.utilisateurCreateurEntity);
        // endregion

        // region definition des roles du projet
            //region droits
        this.droitTache = this.droitProjetRepository.save(new DroitProjetEntity(null, "Assigner un esclave rebel"));
        this.droitCollaborateur = this.droitProjetRepository.save(new DroitProjetEntity(null, "Tuer un sous-fifre "));
            // endregion
        // region role Empereur
        RoleProjetEntity role=new RoleProjetEntity();
        role.setNomDeRole(roleEmpereur);
        Set<DroitProjetEntity> droitEmpereurEntities=new HashSet<>();
        droitEmpereurEntities.add(this.droitTache);
        droitEmpereurEntities.add(this.droitCollaborateur);
        droitEmpereurEntities.add(this.droitChangerOrdeEtape);
        role.setDroitProjets(droitEmpereurEntities);
        this.roleEmpereurEntity = this.roleProjetRepository.save(role);
        // endregion empereur

        // region role sith
        role =new RoleProjetEntity();
        role.setNomDeRole(roleSith);
        Set<DroitProjetEntity> droitSithEntities=new HashSet<>();
        droitSithEntities.add(this.droitChangerOrdeEtape);
        droitSithEntities.add(this.droitTache);
        role.setDroitProjets(droitSithEntities);
        this.roleSithEntity = this.roleProjetRepository.save(role);

        // endregion

        // region role soldat
        role =new RoleProjetEntity();
        role.setNomDeRole(roleStormtrooper);
        Set<DroitProjetEntity> droitStormtrooperEntities=new HashSet<>();
        droitStormtrooperEntities.add(this.droitTache);
        role.setDroitProjets(droitStormtrooperEntities);
        this.roleStormtrooperEntity = this.roleProjetRepository.save(role);

        // endregion

        Set<RoleProjetEntity> roleProjetEntities=new HashSet<>();
        roleProjetEntities.add(this.roleEmpereurEntity);
        roleProjetEntities.add(this.roleSithEntity);
        roleProjetEntities.add(this.roleStormtrooperEntity);
        projet.setRolesProjet(roleProjetEntities);
        // endregion

        this.projetEntity = this.projetRepository.save(projet);

        // region participation
        ParticipationEntity participationEmpereurEntity = new ParticipationEntity();
        participationEmpereurEntity.setUtilisateurParticipant(this.utilisateurCreateurEntity);
        participationEmpereurEntity.setRoleDuParticipant(this.roleEmpereurEntity);
        participationEmpereurEntity.setProjetParticipation(this.projetEntity);
        this.participationEntity = this.participationRepository.save(participationEmpereurEntity);

        this.projetEntity.getMembresDuProjet().add(this.participationEntity);
        this.utilisateurCreateurEntity.getProjetsParticiperUtilisateur().add(this.participationEntity);
        this.projetEntity = this.projetRepository.save(this.projetEntity);
        this.utilisateurRepository.save(this.utilisateurCreateurEntity);
        // endregion
    }

    @Et("avec une liste d'étapes workflow avec leurs numero d'ordre :")
    public void creerListeEtape (List<List<String>> etapeWorkflowEntityList)
    {
        this.etapeWorkflowEntitySet = new HashSet<>();

        //TODO DAMIEN : changer vers une hashMap
        etapeWorkflowEntityList.forEach(l -> {
            EtapeWorkflowEntity etape = new EtapeWorkflowEntity();
            etape.setNomEtapeWorkflow(l.get(0));
            etape.setNumOrdreEtapeWorkflow(Integer.parseInt(l.get(1)));
            etape.setDescriptionEtapeWorkflow("cucumber Test");
            etape.setEstPrenableEtapeWorkflow(true);
            this.etapeWorkflowEntitySet.add(this.etapeWorkflowRepository.save(etape));
        });
        this.projetEntity.setEtapeWorkflows(this.etapeWorkflowEntitySet);
        this.projetEntity = this.projetRepository.save(this.projetEntity);
    }

    // region Scénario: Un membre de projet qui a le droit va changer l'ordre d'une colonne

    @Etantdonné("un nouvel utilisateur {utilisateur}")
    public void creerUtilisateurMembreProjet(UtilisateurEntity utilisateur) {
        this.utilisateurMembreEntity = this.utilisateurRepository.save(utilisateur);
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
        this.participationMembre = this.participationRepository.save(participationMembreEntity);

        this.projetEntity.getMembresDuProjet().add(participationMembreEntity);
        this.projetRepository.save(this.projetEntity);

    }

    @Quand("l'utilisateur veut changer l'ordre de l'étape {string} en ordre {int}")
    public void changerOrdre (String etape,int newOrdre)
    {
       EtapeWorkflowEntity etapeWorkflowEntity = this.etapeWorkflowEntitySet.stream().filter(e -> e.getNomEtapeWorkflow().equals(etape)).findFirst().orElse(null);
        this.result = this.changerOrdreEtapeService.execute(this.utilisateurMembreEntity.getIdUtilisateur(),etapeWorkflowEntity.getIdEtapeWorkflow(),newOrdre);
        this.projetEntity = this.projetRepository.findById(this.projetEntity.getIdProjet()).orElse(null);
    }

    @Alors("l'ordre des étapes devient {string} = {int}, {string} = {int}, {string} = {int}, {string} = {int} de plus le service renvoie {string}")
    public void ordreEstchange(String etape1,int ordreEtape1,String etape2,int ordreEtape2,String etape3,int ordreEtape3,String etape4,int ordreEtape4,String resultat )
    {
        //TODO DAMIEN : modifier le "alors" en un tableau
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

    @After("@changerOrdre")
    public void cleanDatabase ()
    {

        // mise a null de participation pour casser la relation participation-projet-utilisateur
        this.participationMembre.setProjetParticipation(null);
        this.participationMembre.setUtilisateurParticipant(null);
        this.participationEntity.setProjetParticipation(null);
        this.participationEntity.setUtilisateurParticipant(null);
        this.participationRepository.save(this.participationMembre);
        this.participationRepository.save(this.participationEntity);

        this.projetRepository.deleteById(this.projetEntity.getIdProjet());

        this.utilisateurRepository.deleteById(this.utilisateurCreateurEntity.getIdUtilisateur());
        this.utilisateurRepository.deleteById(this.utilisateurMembreEntity.getIdUtilisateur());


        this.participationRepository.deleteById(this.participationMembre.getIdParticipation());
        this.participationRepository.deleteById(this.participationEntity.getIdParticipation());

        this.roleProjetRepository.deleteById(this.roleEmpereurEntity.getIdRole());
        this.roleProjetRepository.deleteById(this.roleSithEntity.getIdRole());
        this.roleProjetRepository.deleteById(this.roleStormtrooperEntity.getIdRole());

        this.droitProjetRepository.deleteById(this.droitTache.getIdDroit());
        this.droitProjetRepository.deleteById(this.droitCollaborateur.getIdDroit());

        this.etapeWorkflowRepository.deleteAll(this.etapeWorkflowEntitySet);

    }

}

package be.technobel.ucmppg.cucumber;

import be.technobel.ucmppg.bl.service.creation.CreationDeProjetService;
import be.technobel.ucmppg.bl.service.projet.AjouterCollaborateurAuProjetService;
import be.technobel.ucmppg.dal.entities.ProjetEntity;
import be.technobel.ucmppg.dal.entities.RoleProjetEntity;
import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import be.technobel.ucmppg.dal.repositories.ProjetRepository;
import be.technobel.ucmppg.dal.repositories.RoleProjetRepository;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import io.cucumber.java.After;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

public class AjouterCollaborateurAuProjetServiceTests {

    private UtilisateurEntity utilisateurEntity = null;
    private ProjetEntity projetEntity = null;

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private RoleProjetRepository roleProjetRepository;
    @Autowired
    private AjouterCollaborateurAuProjetService ajouterCollaborateurAuProjetService;
    @Autowired
    private CreationDeProjetService creationDeProjetService;

    @Etantdonné("un utilisateur")
    public void createUtilisateur(){
        this.utilisateurEntity=new UtilisateurEntity();
        this.utilisateurEntity.setEmailUtilisateur("pierre.dupont@exemple.com");
        this.utilisateurEntity.setPseudoUtilisateur("pierre_dupont");
        this.utilisateurEntity.setPassword("Test1234!");
        this.utilisateurRepository.save(this.utilisateurEntity);
    }

    @Etantdonné("un projet")
    public void createProjet(){


        this.projetEntity=new ProjetEntity();
        projetEntity.setNomDeProjet("projet exemple");
        RoleProjetEntity roleMembre=new RoleProjetEntity();
        roleMembre.setNomDeRole("membre");
        roleProjetRepository.save(roleMembre);
        projetEntity.getRolesProjet().add(roleMembre);
        projetRepository.save(projetEntity);
    }

    @Quand("j'ajoute l'utilisateur au projet")
    public void ajouterUtilisateurAuProjet(){

        ajouterCollaborateurAuProjetService.execute(
                this.projetEntity.getIdProjet(),
                this.utilisateurEntity.getEmailUtilisateur()

        );
    }

    @Alors("l'utilisateur possède une référence vers le projet")
    public void verifierReferenceUtilisateur(){

        this.utilisateurEntity = this.utilisateurRepository.findById(this.utilisateurEntity.getIdUtilisateur()).orElse(null);

        long idProjet = this.projetEntity.getIdProjet();

        //recherche si un élément dans les participations de l'utilisateur possède
        // l'id de projet correspondant à celui du projet auquel on a ajouté l'utilisateur
        Assert.assertTrue(
                this.utilisateurEntity.getProjetsParticiperUtilisateur()
                        .stream()
                        .anyMatch(
                                participationEntity -> participationEntity
                                        .getProjetParticipation()
                                        .getIdProjet()
                                        .equals(
                                                this.projetEntity.getIdProjet()
                                        )
                        )
        );
    }

    @Alors("le projet possède une référence vers le membre")
    public void verifierReferenceProjet() {
        this.projetEntity = this.projetRepository.findById(this.projetEntity.getIdProjet()).orElse(null);
        long idUtilisateur = this.utilisateurEntity.getIdUtilisateur();

        Assert.assertTrue(
                this.projetEntity.getMembresDuProjet().stream()
                        .anyMatch(
                                participationEntity ->
                                        participationEntity.getUtilisateurParticipant().getIdUtilisateur()
                                                .equals(
                                                        idUtilisateur
                                                )

                )
        );
    }

    @After("@ajouterCollabo")
    public void apres(){
        long idUtilisateur=this.utilisateurEntity.getIdUtilisateur();
        long idProjet=this.projetEntity.getIdProjet();
        if(this.utilisateurRepository.findById(idUtilisateur).isPresent()){
            this.utilisateurRepository.deleteById(idUtilisateur);
        }

        if (this.projetRepository.findById(idProjet).isPresent()) {
            this.projetRepository.deleteById(idProjet);
        }
        //TODO : erreur lors du delete de l utilisateur (lien participation ?)

    }
}

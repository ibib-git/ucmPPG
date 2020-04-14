package be.technobel.ucmppg.cucumber;

import be.technobel.ucmppg.bl.dto.RoleDTO;
import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.dto.projet.workflow.EtapeWorkflowDTO;
import be.technobel.ucmppg.bl.service.creation.CreationDeProjetService;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


public class ProjetDTOCreationTest {

    private UtilisateurEntity utilisateurTest = null;
    private String description = null;
    private String nomDuProjet = null;
    private ProjetDTO projetATester = null;

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private CreationDeProjetService creationDeProjetService;

    @Given("je veux un Utilisateur")
    public void CreationUtilisateur(){
        Optional<UtilisateurEntity> optionalUtilisateurEntity = utilisateurRepository.findById(1L);
        optionalUtilisateurEntity.ifPresent(utilisateurEntity -> utilisateurTest = utilisateurEntity);
    }

    @And("nom {string}")
    public void initNom(String value){
        this.nomDuProjet = value;
    }

    @And("descrip {string}")
    public void initdescript(String value){
        this.description = value;
    }

    @When("le projet nom {string}")
    public void initProjetNom(String value){
        projetATester = creationDeProjetService.execute(nomDuProjet,description,utilisateurTest.getIdUtilisateur());
        Assert.assertEquals(value,projetATester.getNom());
    }

    @When("le projet desc {string}")
    public void initProjetDescr(String value){
        projetATester = creationDeProjetService.execute(nomDuProjet,description,utilisateurTest.getIdUtilisateur());
        Assert.assertEquals(value,projetATester.getDescription());
    }

    @When("le createur nom {string}")
    public void le_Createur(String value){
        projetATester = creationDeProjetService.execute(nomDuProjet,description,utilisateurTest.getIdUtilisateur());
        Assert.assertEquals(value,projetATester.getCreateurUtilisateur().getPseudo());
    }

    @When("Etape {string}")
    public void etape(String value){
        projetATester = creationDeProjetService.execute(nomDuProjet,description,utilisateurTest.getIdUtilisateur());
        for (EtapeWorkflowDTO d: projetATester.getEtapeWorkflows()) {
            if(d.getNom().equals(value)){
                Assert.assertEquals(value,d.getNom());
            }
        }
    }

    @When("role {string}")
    public void role(String value){
        projetATester = creationDeProjetService.execute(nomDuProjet,description,utilisateurTest.getIdUtilisateur());
        for (RoleDTO d: projetATester.getRoles()) {
            if(d.getNom().equals(value)){
                Assert.assertEquals(value,d.getNom());
            }
        }
    }

}

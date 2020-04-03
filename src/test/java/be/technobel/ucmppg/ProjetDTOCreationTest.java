package be.technobel.ucmppg;

import be.technobel.ucmppg.bl.dto.RoleDTO;
import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.dto.projet.workflow.EtapeWorkflowDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDTO;
import be.technobel.ucmppg.bl.service.creation.CreationDeProjetService;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
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
        Assert.assertEquals(value,projetATester.getNomProjet());
    }

    @When("le projet desc {string}")
    public void initProjetDescr(String value){
        projetATester = creationDeProjetService.execute(nomDuProjet,description,utilisateurTest.getIdUtilisateur());
        Assert.assertEquals(value,projetATester.getDescriptionProjet());
    }

    @When("le createur nom {string}")
    public void le_Createur(String value){
        projetATester = creationDeProjetService.execute(nomDuProjet,description,utilisateurTest.getIdUtilisateur());
        Assert.assertEquals(value,projetATester.getUtilisateurCreateurProjet().getPseudo());
    }

    @When("Etape {string}")
    public void etape(String value){
        projetATester = creationDeProjetService.execute(nomDuProjet,description,utilisateurTest.getIdUtilisateur());
        for (EtapeWorkflowDTO d: projetATester.getColonnesDuProjet()) {
            if(d.getNomWorkflow().equals(value)){
                Assert.assertEquals(value,d.getNomWorkflow());
            }
        }
    }

    @When("role {string}")
    public void role(String value){
        projetATester = creationDeProjetService.execute(nomDuProjet,description,utilisateurTest.getIdUtilisateur());
        for (RoleDTO d: projetATester.getRoleDuProjets()) {
            if(d.getNom().equals(value)){
                Assert.assertEquals(value,d.getNom());
            }
        }
    }

}

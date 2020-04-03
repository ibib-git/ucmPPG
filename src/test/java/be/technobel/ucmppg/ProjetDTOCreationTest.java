package be.technobel.ucmppg;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.dto.projet.workflow.EtapeWorkflowDTO;
import be.technobel.ucmppg.bl.service.creation.CreationDeProjetService;
import be.technobel.ucmppg.dal.entities.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

public class ProjetDTOCreationTest {

    @Autowired
    private CreationDeProjetService creationDeProjetService;
    private ProjetDTO projetATester;

    @Before
    public void init(){
        System.out.println("Test");
        projetATester = creationDeProjetService.execute("La Bouffe","j aime mangé de la bouffe",4L);

    }

    @Test
    public void ProjetDTO_NomDuProjet(){
        Assert.assertEquals(projetATester.getNomProjet(),"la bouffe");
    }

    @Test
    public void ProjetDTO_DescriptionDuProjet(){
        Assert.assertEquals(projetATester.getDescriptionProjet(),"j aime mangé de la bouffe");
    }

    @Test
    public void ProjetDTO_LesEtapeWorkflowAFaire(){
        for (EtapeWorkflowDTO etape: projetATester.getColonnesDuProjet()) {
            Assert.assertEquals("à faire", etape.getNomWorkflow());
        }
    }

    @Test
    public void ProjetDTO_LesEtapeWorkflowEnCours(){
        for (EtapeWorkflowDTO etape: projetATester.getColonnesDuProjet()) {
            Assert.assertEquals("en cours", etape.getNomWorkflow());
        }
    }

    @Test
    public void ProjetDTO_LesEtapeWorkflowTerminer(){
        for (EtapeWorkflowDTO etape: projetATester.getColonnesDuProjet()) {
            Assert.assertEquals("fini", etape.getNomWorkflow());
        }
    }

    @Test
    public void ProjetDTO_Utilisateur_Createur(){

        UtilisateurEntity utilisateurTest = new UtilisateurEntity();
        utilisateurTest.setEmailUtilisateur("Gros@gmail.com");
        utilisateurTest.setMotDePasseUtilisateur("Test1234&");
        utilisateurTest.setPseudoUtilisateur("Hamburger");
        utilisateurTest.setNomUtilisateur("Mac");
        utilisateurTest.setPrenomUtilisateur("Donald");
        utilisateurTest.setInformationSupplementaireUtilisateur("la vie estle gras");
        utilisateurTest.setTelephoneUtilisateur("0123456789");
        Assert.assertSame(projetATester.getUtilisateurCreateurProjet(),utilisateurTest);
    }

}

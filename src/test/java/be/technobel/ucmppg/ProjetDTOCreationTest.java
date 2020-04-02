package be.technobel.ucmppg;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.dto.projet.workflow.EtapeWorkflowDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;

import be.technobel.ucmppg.bl.service.creation.CreationDeProjetService;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.Optional;

public class ProjetDTOCreationTest {

    private CreationDeProjetService creationDeProjetService;
    private ProjetDTO projetATester;

    @Before
    public void init(){
        System.out.println("Test");
    }

    @Test
    public void ProjetDTO_NomDuProjet(){
        projetATester = creationDeProjetService.execute("la bouffe","j aime mangé de la bouffe", 4L);
        Assert.assertEquals(projetATester.getNomProjet(),"la bouffe");
    }

    @Test
    public void ProjetDTO_DescriptionDuProjet(){
        projetATester = creationDeProjetService.execute("La Bouffe","j aime mangé de la bouffe",4L);
        Assert.assertEquals(projetATester.getDescriptionProjet(),"j aime mangé de la bouffe");
    }

    @Test
    public void ProjetDTO_LesEtapeWorkflowAFaire(){
        projetATester = creationDeProjetService.execute("La Bouffe","j aime mangé de la bouffe",4L);
        for (EtapeWorkflowDTO etape: projetATester.getColonnesDuProjet()) {
            Assert.assertEquals("à faire", etape.getNomWorkflow());
        }
    }

    @Test
    public void ProjetDTO_LesEtapeWorkflowEnCours(){
        projetATester = creationDeProjetService.execute("La Bouffe","j aime mangé de la bouffe",4L);
        for (EtapeWorkflowDTO etape: projetATester.getColonnesDuProjet()) {
            Assert.assertEquals("en cours", etape.getNomWorkflow());
        }
    }

    @Test
    public void ProjetDTO_LesEtapeWorkflowTerminer(){
        projetATester = creationDeProjetService.execute("La Bouffe","j aime mangé de la bouffe",4L);
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

        projetATester = creationDeProjetService.execute("pas grave","aie",4L);
        Assert.assertSame(projetATester.getUtilisateurCreateurProjet(),utilisateurTest);
    }

}

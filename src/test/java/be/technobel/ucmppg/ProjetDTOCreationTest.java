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

    private UtilisateurRepository utilisateurRepository;
    private CreationDeProjetService creationDeProjetService;
    private ProjetDTO projetATester;
    private UtilisateurEntity utilisateurEntityPourTest;
    private UtilisateurDetailsDTO utilisateurComparaison;

    @Before
    public void init(){
        UtilisateurEntity utilisateurTest = new UtilisateurEntity();
        utilisateurTest.setEmailUtilisateur("Gros@gmail.com");
        utilisateurTest.setMotDePasseUtilisateur("Test1234&");
        utilisateurTest.setPseudoUtilisateur("Hamburger");
        utilisateurTest.setNomUtilisateur("Mac");
        utilisateurTest.setPrenomUtilisateur("Donald");
        utilisateurTest.setInformationSupplementaireUtilisateur("la vie estle gras");
        utilisateurTest.setTelephoneUtilisateur("0123456789");
        utilisateurRepository.save(utilisateurTest);

        Optional<UtilisateurEntity> optinalUtilisateur = utilisateurRepository.findByEmailUtilisateur("Gros@gmail.com");
        optinalUtilisateur.ifPresent(utilisateurEntity -> utilisateurEntityPourTest = utilisateurEntity);
    }

    @Test
    public void ProjetDTO_NomDuProjet(){
        projetATester = creationDeProjetService.execute("la bouffe","j aime mangé de la bouffe",utilisateurEntityPourTest.getIdUtilisateur());
        Assert.assertEquals(projetATester.getNomProjet(),"la bouffe");
    }

    @Test
    public void ProjetDTO_DescriptionDuProjet(){
        projetATester = creationDeProjetService.execute("La Bouffe","j aime mangé de la bouffe",1L);
        Assert.assertEquals(projetATester.getDescriptionProjet(),"j aime mangé de la bouffe");
    }

    @Test
    public void ProjetDTO_LesEtapeWorkflowAFaire(){
        projetATester = creationDeProjetService.execute("La Bouffe","j aime mangé de la bouffe",1L);
        for (EtapeWorkflowDTO etape: projetATester.getColonnesDuProjet()) {
            Assert.assertEquals("à faire", etape.getNomWorkflow());
        }
    }

    @Test
    public void ProjetDTO_LesEtapeWorkflowEnCours(){
        projetATester = creationDeProjetService.execute("La Bouffe","j aime mangé de la bouffe",1L);
        for (EtapeWorkflowDTO etape: projetATester.getColonnesDuProjet()) {
            Assert.assertEquals("en cours", etape.getNomWorkflow());
        }
    }

    @Test
    public void ProjetDTO_LesEtapeWorkflowTerminer(){
        projetATester = creationDeProjetService.execute("La Bouffe","j aime mangé de la bouffe",1L);
        for (EtapeWorkflowDTO etape: projetATester.getColonnesDuProjet()) {
            Assert.assertEquals("fini", etape.getNomWorkflow());
        }
    }

    @Test
    public void ProjetDTO_Utilisateur_Createur(){

        UtilisateurEntity utilisateur2=new UtilisateurEntity();
        utilisateur2.setPseudoUtilisateur("toto");
        utilisateur2.setEmailUtilisateur("thomas@ppg.com");
        utilisateur2.setInformationSupplementaireUtilisateur("il aime pas les champignons");
        utilisateur2.setMotDePasseUtilisateur("Test1234!");
        utilisateur2.setNomUtilisateur("Wattecamps");
        utilisateur2.setPrenomUtilisateur("Thomas");

        projetATester = creationDeProjetService.execute("pas grave","aie",1L);
        Assert.assertSame(projetATester.getUtilisateurCreateurProjet(),utilisateurComparaison);
    }

}

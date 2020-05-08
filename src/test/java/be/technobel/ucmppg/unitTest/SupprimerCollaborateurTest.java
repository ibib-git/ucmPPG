package be.technobel.ucmppg.unitTest;


import be.technobel.ucmppg.bl.dto.projet.collaborateur.SupprimerCollaborateurDTO;
import be.technobel.ucmppg.bl.service.projet.SupprimerCollaborateurDuProjetService;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.*;
import org.junit.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SupprimerCollaborateurTest {

    @Autowired
    private SupprimerCollaborateurDuProjetService suppressionTest;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private EtapeWorkflowRepository etapeWorkflowRepository;
    @Autowired
    private RoleProjetRepository roleProjetRepository;
    @Autowired
    private HistoriqueTacheRepository historiqueTacheRepository;

    private SupprimerCollaborateurDTO supprimerColl;
    private UtilisateurEntity utilisateur;
    private ProjetEntity projet;
    private ParticipationEntity participation;
    private List<TacheEntity> tacheEntityList;


    @Before
    public void init() { System.out.println("Début du test"); }

    @After
    public void last() { System.out.println("Fin du test"); }

    @Test
    public void ValidationDuService(){
        supprimerColl = new SupprimerCollaborateurDTO("damien@ppg.com",1L);
        Assert.assertTrue(suppressionTest.execute(supprimerColl));
    }

    @Test
    public void verifiactionDeLHistoriqueDeTache(){
        supprimerColl = new SupprimerCollaborateurDTO("damien@ppg.com",1L);
        suppressionTest.execute(supprimerColl);
        // Tache de Utilisateur.gertId = 3
        Optional<TacheEntity> optionalTacheEntity =tacheRepository.findByNomTache("réaliser le schéma db");
        Assert.assertNull(optionalTacheEntity.get().getUtilisateur_Tache());

    }

    @Test
    public void verifiactionDeLUtilisateurDeLaTache(){
        supprimerColl = new SupprimerCollaborateurDTO("damien@ppg.com",1L);
        suppressionTest.execute(supprimerColl);
        Optional<UtilisateurEntity> optionalUtilisateurEntity = utilisateurRepository.findByEmailUtilisateur("damien@ppg.com");
        Optional<List<TacheEntity>> optionalTacheEntities = tacheRepository.findByProjetAndUtilisateur(1L,3L);
        if(optionalTacheEntities.isPresent()){
            Assert.assertTrue(optionalTacheEntities.get().isEmpty());
        }
    }

    @Test
    public void verificationDuChangementDeParticipation_isActif_false(){
        supprimerColl = new SupprimerCollaborateurDTO("damien@ppg.com",1L);
        suppressionTest.execute(supprimerColl);
        Optional<UtilisateurEntity> optionalUtilisateurEntity = utilisateurRepository.findByEmailUtilisateur("damien@ppg.com");
        utilisateur = optionalUtilisateurEntity.get();
        Optional<ParticipationEntity> optionalParticipationEntity = participationRepository.findByParticipationOfProjet(3L,1L);
        Assert.assertFalse(optionalParticipationEntity.get().isActif());
    }

}

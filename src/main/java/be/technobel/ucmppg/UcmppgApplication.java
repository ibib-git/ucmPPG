package be.technobel.ucmppg;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.dto.projet.workflow.EtapeWorkflowDTO;
import be.technobel.ucmppg.bl.service.creation.CreationDeProjetService;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.servlet.http.Part;
import java.util.HashSet;

@SpringBootApplication
@EnableSwagger2
@EntityScan({"be.technobel.ucmppg.dal.entities"})
public class UcmppgApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcmppgApplication.class, args);
    }
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private DroitProjetRepository droitProjetRepository;
    @Autowired
    private RoleProjetRepository roleProjetRepository;
    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private EtapeWorkflowRepository etapeWorkflowRepository;
    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private ProjetRepository projetRepository;

    @Autowired
    private CreationDeProjetService service;

    @EventListener(ApplicationReadyEvent.class)
    public void generateData(){

        System.out.println("GENERATING DATAS");

        UtilisateurEntity utilisateur=new UtilisateurEntity();
        utilisateur.setPseudoUtilisateur("baba");
        utilisateur.setEmailUtilisateur("bastien@ppg.com");
        utilisateur.setInformationSupplementaireUtilisateur("c'est un sacré filou");
        utilisateur.setMotDePasseUtilisateur("Test1234!");
        utilisateur.setNomUtilisateur("Housiaux");
        utilisateur.setPrenomUtilisateur("Bastien");
        utilisateur.setProjetsParticiperUtilisateur(new HashSet<ParticipationEntity>());

        utilisateurRepository.save(utilisateur);

        UtilisateurEntity utilisateur2=new UtilisateurEntity();
        utilisateur2.setPseudoUtilisateur("toto");
        utilisateur2.setEmailUtilisateur("thomas@ppg.com");
        utilisateur2.setInformationSupplementaireUtilisateur("il aime pas les champignons");
        utilisateur2.setMotDePasseUtilisateur("Test1234!");
        utilisateur2.setNomUtilisateur("Wattecamps");
        utilisateur2.setPrenomUtilisateur("Thomas");
        utilisateur2.setProjetsParticiperUtilisateur(new HashSet<ParticipationEntity>());

        utilisateurRepository.save(utilisateur2);

        UtilisateurEntity utilisateur3=new UtilisateurEntity();
        utilisateur3.setPseudoUtilisateur("dada");
        utilisateur3.setEmailUtilisateur("damien@ppg.com");
        utilisateur3.setInformationSupplementaireUtilisateur("il a 32 ans et deux enfants");
        utilisateur3.setMotDePasseUtilisateur("Test1234!");
        utilisateur3.setNomUtilisateur("Fricot");
        utilisateur3.setPrenomUtilisateur("Damien");
        utilisateur3.setProjetsParticiperUtilisateur(new HashSet<ParticipationEntity>());

        utilisateurRepository.save(utilisateur3);

        DroitProjetEntity gererTache=new DroitProjetEntity();
        gererTache.setNomDroit("gérer les taches");
        droitProjetRepository.save(gererTache);

        DroitProjetEntity gererRole=new DroitProjetEntity();
        gererRole.setNomDroit("gérer les rôles");
        droitProjetRepository.save(gererTache);

        DroitProjetEntity creerTache=new DroitProjetEntity();
        creerTache.setNomDroit("Créer des tâches");
        droitProjetRepository.save(creerTache);

        DroitProjetEntity creerRole=new DroitProjetEntity();
        creerRole.setNomDroit("Créer des rôles");
        droitProjetRepository.save(creerRole);

        DroitProjetEntity inviterCollaborateurs=new DroitProjetEntity();
        inviterCollaborateurs.setNomDroit("inviter des collaborateurs");
        droitProjetRepository.save(inviterCollaborateurs);

        DroitProjetEntity prendreTache=new DroitProjetEntity();
        prendreTache.setNomDroit("prendre une tâche");
        droitProjetRepository.save(prendreTache);

        RoleProjetEntity admin=new RoleProjetEntity();
        admin.setNomDeRole("administrateur");
        admin.getDroitProjets().add(gererTache);
        admin.getDroitProjets().add(inviterCollaborateurs);
        admin.getDroitProjets().add(prendreTache);
        roleProjetRepository.save(admin);

        RoleProjetEntity moderateur=new RoleProjetEntity();
        moderateur.setNomDeRole("modérateur");
        moderateur.getDroitProjets().add(gererTache);
        moderateur.getDroitProjets().add(prendreTache);
        roleProjetRepository.save(moderateur);

        RoleProjetEntity membre=new RoleProjetEntity();
        membre.setNomDeRole("membre");
        membre.getDroitProjets().add(prendreTache);
        roleProjetRepository.save(membre);

        ProjetEntity projet1 =new ProjetEntity();
        projet1.setNomDeProjet("The cheese project");
        projet1.setDescriptionDeProjet("By Technobel studio");
        projet1.setUtilisateurCreateur(utilisateur);

        projetRepository.save(projet1);

        ParticipationEntity participation=new ParticipationEntity();
        participation.setUtilisateurParticipant(utilisateur);
        participation.setRoleDuParticipant(admin);
        participation.setProjetParticipation(projet1);
        participationRepository.save(participation);

        projet1.getMembresDuProjet().add(participation);
        utilisateur.getProjetsParticiperUtilisateur().add(participation);

        ParticipationEntity participation2=new ParticipationEntity();
        participation2.setUtilisateurParticipant(utilisateur2);
        participation2.setRoleDuParticipant(moderateur);
        participation2.setProjetParticipation(projet1);
        participationRepository.save(participation2);

        projet1.getMembresDuProjet().add(participation2);

        ParticipationEntity participation3=new ParticipationEntity();
        participation3.setUtilisateurParticipant(utilisateur3);
        participation3.setRoleDuParticipant(membre);
        participation3.setProjetParticipation(projet1);
        participationRepository.save(participation3);

        projet1.getMembresDuProjet().add(participation3);

        EtapeWorkflowEntity todo=new EtapeWorkflowEntity();
        todo.setConstrainteAffectation(ConstrainteAffectationEnum.AUCUN);
        todo.setEstPrenableEtapeWorkflow(true);
        todo.setNomEtapeWorkflow("To Do");
        todo.setDescriptionEtapeWorkflow("A faire");
        todo.getRolesAutorisation().add(admin);
        todo.getRolesAutorisation().add(moderateur);
        todo.getRolesAutorisation().add(membre);

        etapeWorkflowRepository.save(todo);

        EtapeWorkflowEntity doing=new EtapeWorkflowEntity();
        doing.setConstrainteAffectation(ConstrainteAffectationEnum.MEME);
        doing.setEstPrenableEtapeWorkflow(true);
        doing.setNomEtapeWorkflow("Doing");
        doing.setDescriptionEtapeWorkflow("En cours");
        doing.getRolesAutorisation().add(admin);
        doing.getRolesAutorisation().add(moderateur);
        doing.getRolesAutorisation().add(membre);

        etapeWorkflowRepository.save(doing);

        EtapeWorkflowEntity done=new EtapeWorkflowEntity();
        done.setConstrainteAffectation(ConstrainteAffectationEnum.MEME);
        done.setEstPrenableEtapeWorkflow(false);
        done.setNomEtapeWorkflow("Done");
        done.setDescriptionEtapeWorkflow("Fini");
        done.getRolesAutorisation().add(admin);
        done.getRolesAutorisation().add(moderateur);
        done.getRolesAutorisation().add(membre);

        etapeWorkflowRepository.save(done);

        projet1.getEtapeWorkflows().add(todo);
        projet1.getEtapeWorkflows().add(doing);
        projet1.getEtapeWorkflows().add(done);

        TacheEntity tache1=new TacheEntity();
        tache1.setNomTache("réaliser le schéma db");
        tache1.setDescriptionTache("blablabla vive l'analyse");
        tache1.setEstimationDeTemps_Tache(12);
        tache1.setUniteDeTemps_tache(UniteDeTempsEnum.STORYPOINT);
        tache1.setUtilisateur_Tache(utilisateur3);

        TacheEntity tache2=new TacheEntity();
        tache2.setNomTache("faire la DAL");
        tache2.setDescriptionTache("faire la couche DAL sur base du schéma fourni");
        tache2.setEstimationDeTemps_Tache(8);
        tache2.setUniteDeTemps_tache(UniteDeTempsEnum.STORYPOINT);
        tache2.setUtilisateur_Tache(utilisateur2);

        tache1.getTachesPrecedentes().add(tache2);

        TacheEntity tache3=new TacheEntity();
        tache3.setNomTache("créer une voiture");
        tache3.setDescriptionTache("vroum vroum fait la voiture");
        tache3.setEstimationDeTemps_Tache(20);
        tache3.setUniteDeTemps_tache(UniteDeTempsEnum.STORYPOINT);
        tache3.setUtilisateur_Tache(utilisateur);

        TacheEntity tache4=new TacheEntity();
        tache4.setNomTache("réalisation du moteur");
        tache4.setDescriptionTache("y a de la mécanique");
        tache4.setEstimationDeTemps_Tache(8);
        tache4.setUniteDeTemps_tache(UniteDeTempsEnum.STORYPOINT);
        tache4.setUtilisateur_Tache(utilisateur);

        TacheEntity tache5=new TacheEntity();
        tache5.setNomTache("réalisation de la carosserie");
        tache5.setDescriptionTache("et que ca brille hein");
        tache5.setEstimationDeTemps_Tache(12);
        tache5.setUniteDeTemps_tache(UniteDeTempsEnum.STORYPOINT);
        tache5.setUtilisateur_Tache(utilisateur);

        tacheRepository.save(tache4);
        tacheRepository.save(tache5);

        tache3.getTachesEnfants().add(tache4);
        tache3.getTachesEnfants().add(tache5);

        tacheRepository.save(tache2);
        tacheRepository.save(tache1);
        tacheRepository.save(tache3);

        todo.getTaches().add(tache2);
        todo.getTaches().add(tache3);
        done.getTaches().add(tache1);

        etapeWorkflowRepository.save(todo);
        etapeWorkflowRepository.save(done);

        projetRepository.save(projet1);

        ProjetDTO test = service.execute("divan","sieste",utilisateur.getIdUtilisateur());

        UtilisateurEntity utilisateurTest = new UtilisateurEntity();
        utilisateurTest.setEmailUtilisateur("Gros@gmail.com");
        utilisateurTest.setMotDePasseUtilisateur("Test1234&");
        utilisateurTest.setPseudoUtilisateur("Hamburger");
        utilisateurTest.setNomUtilisateur("Mac");
        utilisateurTest.setPrenomUtilisateur("Donald");
        utilisateurTest.setInformationSupplementaireUtilisateur("la vie estle gras");
        utilisateurTest.setTelephoneUtilisateur("0123456789");

        utilisateurRepository.save(utilisateurTest);


    }

}

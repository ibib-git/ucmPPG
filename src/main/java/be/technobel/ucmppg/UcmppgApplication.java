package be.technobel.ucmppg;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.dto.projet.collaborateur.SupprimerCollaborateurDTO;
import be.technobel.ucmppg.bl.dto.projet.workflow.EtapeWorkflowDTO;
import be.technobel.ucmppg.bl.service.creation.CreationDeProjetService;
import be.technobel.ucmppg.configuration.Constantes;
import be.technobel.ucmppg.bl.service.projet.AjouterCollaborateurAuProjetService;
import be.technobel.ucmppg.bl.service.projet.SupprimerCollaborateurDuProjetService;
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
import java.util.Optional;
import java.util.Set;

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
    private HistoriqueTacheRepository historiqueTacheRepository;

    @Autowired
    private CreationDeProjetService service;

    @Autowired
    private AjouterCollaborateurAuProjetService aCAPS;

    @Autowired
    private SupprimerCollaborateurDuProjetService scdps;

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

        UtilisateurEntity testUtilisateur = new UtilisateurEntity();
        testUtilisateur.setEmailUtilisateur("Gros@gmail.com");
        testUtilisateur.setMotDePasseUtilisateur("Test1234&");
        testUtilisateur.setPseudoUtilisateur("Hamburger");
        testUtilisateur.setNomUtilisateur("Mac");
        testUtilisateur.setPrenomUtilisateur("Donald");
        testUtilisateur.setInformationSupplementaireUtilisateur("le gras c'est la vie");
        testUtilisateur.setTelephoneUtilisateur("0123456789");
        testUtilisateur.setProjetsParticiperUtilisateur(new HashSet<ParticipationEntity>());

        utilisateurRepository.save(testUtilisateur);

        DroitProjetEntity gererTache=new DroitProjetEntity();
        gererTache.setNomDroit(Constantes.DROIT_GERER_TACHES);
        droitProjetRepository.save(gererTache);

        DroitProjetEntity gererRole=new DroitProjetEntity();
        gererRole.setNomDroit(Constantes.DROIT_GERER_ROLES);
        droitProjetRepository.save(gererTache);

        DroitProjetEntity creerTache=new DroitProjetEntity();
        creerTache.setNomDroit(Constantes.DROIT_CREER_TACHES);
        droitProjetRepository.save(creerTache);

        DroitProjetEntity creerRole=new DroitProjetEntity();
        creerRole.setNomDroit(Constantes.DROIT_CREER_ROLES);
        droitProjetRepository.save(creerRole);

        DroitProjetEntity inviterCollaborateurs=new DroitProjetEntity();
        inviterCollaborateurs.setNomDroit(Constantes.DROIT_INVITER_COLLABORATEURS);
        droitProjetRepository.save(inviterCollaborateurs);

        DroitProjetEntity prendreTache=new DroitProjetEntity();
        prendreTache.setNomDroit(Constantes.DROIT_PRENDRE_TACHE);
        droitProjetRepository.save(prendreTache);

        DroitProjetEntity changerEtapeOrdre=new DroitProjetEntity();
        changerEtapeOrdre.setNomDroit(Constantes.DROIT_CHANGER_ORDRE_ETAPE);
        droitProjetRepository.save(changerEtapeOrdre);

        DroitProjetEntity validerTache = new DroitProjetEntity();
        validerTache.setNomDroit(Constantes.DROIT_VALIDER_TACHE);
        droitProjetRepository.save(validerTache);

        DroitProjetEntity assignerTache = new DroitProjetEntity();
        assignerTache.setNomDroit(Constantes.DROIT_ASSIGNER_TACHE);
        droitProjetRepository.save(assignerTache);

        DroitProjetEntity supprimeCollaboTache = new DroitProjetEntity();
        supprimeCollaboTache.setNomDroit(Constantes.DROIT_SUPPRIMER_COLLABORATEUR_TACHE);
        droitProjetRepository.save(supprimeCollaboTache);


        RoleProjetEntity admin=new RoleProjetEntity();
        admin.setNomDeRole(Constantes.ROLE_ADMINISTRATEUR);
        admin.getDroitProjets().add(gererTache);
        admin.getDroitProjets().add(inviterCollaborateurs);
        admin.getDroitProjets().add(prendreTache);
        admin.getDroitProjets().add(changerEtapeOrdre);
        admin.getDroitProjets().add(validerTache);
        admin.getDroitProjets().add(assignerTache);
        admin.getDroitProjets().add(supprimeCollaboTache);
        roleProjetRepository.save(admin);

        RoleProjetEntity moderateur=new RoleProjetEntity();
        moderateur.setNomDeRole(Constantes.ROLE_MODERATEUR);
        moderateur.getDroitProjets().add(gererTache);
        moderateur.getDroitProjets().add(prendreTache);
        moderateur.getDroitProjets().add(validerTache);
        moderateur.getDroitProjets().add(assignerTache);
        moderateur.getDroitProjets().add(supprimeCollaboTache);
        roleProjetRepository.save(moderateur);

        RoleProjetEntity membre=new RoleProjetEntity();
        membre.setNomDeRole(Constantes.ROLE_MEMBRE);
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
        participation.setActif(true);
        participationRepository.save(participation);

        projet1.getMembresDuProjet().add(participation);
        utilisateur.getProjetsParticiperUtilisateur().add(participation);
        utilisateurRepository.save(utilisateur);

        ParticipationEntity participation2=new ParticipationEntity();
        participation2.setUtilisateurParticipant(utilisateur2);
        participation2.setRoleDuParticipant(moderateur);
        participation2.setProjetParticipation(projet1);
        participation2.setActif(true);
        participationRepository.save(participation2);

        projet1.getMembresDuProjet().add(participation2);
        utilisateur2.getProjetsParticiperUtilisateur().add(participation2);
        utilisateurRepository.save(utilisateur2);

        ParticipationEntity participation3=new ParticipationEntity();
        participation3.setUtilisateurParticipant(utilisateur3);
        participation3.setRoleDuParticipant(membre);
        participation3.setProjetParticipation(projet1);
        participation3.setActif(true);
        participationRepository.save(participation3);

        projet1.getMembresDuProjet().add(participation3);
        utilisateur3.getProjetsParticiperUtilisateur().add(participation3);
        utilisateurRepository.save(utilisateur3);

        ParticipationEntity participation4 = new ParticipationEntity();
        participation4.setUtilisateurParticipant(testUtilisateur);
        participation4.setRoleDuParticipant(membre);
        participation4.setProjetParticipation(projet1);
        participation4.setActif(true);
        participationRepository.save(participation4);

        projet1.getMembresDuProjet().add(participation4);
        testUtilisateur.getProjetsParticiperUtilisateur().add(participation4);
        utilisateurRepository.save(testUtilisateur);

        EtapeWorkflowEntity todo=new EtapeWorkflowEntity();
        todo.setConstrainteAffectation(ConstrainteAffectationEnum.AUCUN);
        todo.setEstPrenableEtapeWorkflow(true);
        todo.setNumOrdreEtapeWorkflow(1);
        todo.setNomEtapeWorkflow("To Do");
        todo.setDescriptionEtapeWorkflow("A faire");
        todo.getRolesAutorisation().add(admin);
        todo.getRolesAutorisation().add(moderateur);
        todo.getRolesAutorisation().add(membre);

        etapeWorkflowRepository.save(todo);

        EtapeWorkflowEntity doing=new EtapeWorkflowEntity();
        doing.setConstrainteAffectation(ConstrainteAffectationEnum.MEME);
        doing.setEstPrenableEtapeWorkflow(true);
        doing.setNumOrdreEtapeWorkflow(2);
        doing.setNomEtapeWorkflow("Doing");
        doing.setDescriptionEtapeWorkflow("En cours");
        doing.getRolesAutorisation().add(admin);
        doing.getRolesAutorisation().add(moderateur);
        doing.getRolesAutorisation().add(membre);

        etapeWorkflowRepository.save(doing);

        EtapeWorkflowEntity done=new EtapeWorkflowEntity();
        done.setConstrainteAffectation(ConstrainteAffectationEnum.MEME);
        done.setEstPrenableEtapeWorkflow(false);
        done.setNumOrdreEtapeWorkflow(3);
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
        tache1.setTachesPrecedentes(new HashSet<TacheEntity>());
        tache1.setTachesEnfants(new HashSet<TacheEntity>());

        tacheRepository.save(tache1);

        TacheEntity tache2=new TacheEntity();
        tache2.setNomTache("faire la DAL");
        tache2.setDescriptionTache("faire la couche DAL sur base du schéma fourni");
        tache2.setEstimationDeTemps_Tache(8);
        tache2.setUniteDeTemps_tache(UniteDeTempsEnum.STORYPOINT);
        tache2.setUtilisateur_Tache(utilisateur2);
        tache2.setTachesPrecedentes(new HashSet<TacheEntity>());
        tache2.setTachesEnfants(new HashSet<TacheEntity>());

        tache2.getTachesPrecedentes().add(tache1);
        tacheRepository.save(tache2);


        TacheEntity tache3=new TacheEntity();
        tache3.setNomTache("créer une voiture");
        tache3.setDescriptionTache("vroum vroum fait la voiture");
        tache3.setEstimationDeTemps_Tache(20);
        tache3.setUniteDeTemps_tache(UniteDeTempsEnum.STORYPOINT);
        tache3.setUtilisateur_Tache(utilisateur);
        tache3.setTachesPrecedentes(new HashSet<TacheEntity>());
        tache3.setTachesEnfants(new HashSet<TacheEntity>());

        tacheRepository.save(tache3);

        TacheEntity tache4=new TacheEntity();
        tache4.setNomTache("réalisation du moteur");
        tache4.setDescriptionTache("y a de la mécanique");
        tache4.setEstimationDeTemps_Tache(8);
        tache4.setUniteDeTemps_tache(UniteDeTempsEnum.STORYPOINT);
        tache4.setUtilisateur_Tache(utilisateur);
        tache4.setTachesPrecedentes(new HashSet<TacheEntity>());
        tache4.setTachesEnfants(new HashSet<TacheEntity>());

        tacheRepository.save(tache4);

        TacheEntity tache5=new TacheEntity();
        tache5.setNomTache("réalisation de la carosserie");
        tache5.setDescriptionTache("et que ca brille hein");
        tache5.setEstimationDeTemps_Tache(12);
        tache5.setUniteDeTemps_tache(UniteDeTempsEnum.STORYPOINT);
        tache5.setUtilisateur_Tache(utilisateur);
        tache5.setTachesPrecedentes(new HashSet<TacheEntity>());
        tache5.setTachesEnfants(new HashSet<TacheEntity>());

        tacheRepository.save(tache4);
        tacheRepository.save(tache5);

        tache3.getTachesEnfants().add(tache4);
        tache3.getTachesEnfants().add(tache5);
        tache3.getTachesPrecedentes().add(tache2);
        tacheRepository.save(tache3);

        todo.getTaches().add(tache2);
        todo.getTaches().add(tache3);
        done.getTaches().add(tache1);

        etapeWorkflowRepository.save(todo);
        etapeWorkflowRepository.save(done);

        projet1.getRolesProjet().add(admin);
        projet1.getRolesProjet().add(membre);
        projet1.getRolesProjet().add(moderateur);

        projetRepository.save(projet1);

        HistoriqueTacheEntity historique = new HistoriqueTacheEntity(null,tache5,todo,utilisateur);
        HistoriqueTacheEntity historique2 = new HistoriqueTacheEntity(null,tache1,todo,utilisateur);
        HistoriqueTacheEntity historique3 = new HistoriqueTacheEntity(null,tache1,doing,utilisateur);
        HistoriqueTacheEntity historique4 = new HistoriqueTacheEntity(null,tache2,todo,utilisateur);
        HistoriqueTacheEntity historique5 = new HistoriqueTacheEntity(null,tache3,todo,utilisateur2);
        HistoriqueTacheEntity historique6 = new HistoriqueTacheEntity(null,tache1,done,utilisateur);
        HistoriqueTacheEntity historique7 = new HistoriqueTacheEntity(null,tache4,todo,utilisateur);
        HistoriqueTacheEntity historique8 = new HistoriqueTacheEntity(null,tache4,doing,utilisateur);

        historiqueTacheRepository.save(historique);
        historiqueTacheRepository.save(historique2);
        historiqueTacheRepository.save(historique3);
        historiqueTacheRepository.save(historique4);
        historiqueTacheRepository.save(historique5);
        historiqueTacheRepository.save(historique6);
        historiqueTacheRepository.save(historique7);
        historiqueTacheRepository.save(historique8);


        ProjetDTO test = service.execute("divan","sieste",utilisateur.getIdUtilisateur());

        aCAPS.execute(test.getId(),utilisateur2.getEmailUtilisateur());
    }

}

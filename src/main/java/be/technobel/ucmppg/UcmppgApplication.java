package be.technobel.ucmppg;

import be.technobel.ucmppg.DAL.Models.Utilisateur;
import be.technobel.ucmppg.DAL.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EntityScan({"be.technobel.ucmppg.DAL.Models"})
public class UcmppgApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcmppgApplication.class, args);
    }

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @EventListener(ApplicationReadyEvent.class)
    private void generateData(){

        System.out.println("GENERATING DATAS");

        Utilisateur utilisateur=new Utilisateur();
        utilisateur.setPseudo_Utilisateur("baba");
        utilisateur.setEmail_Utilisateur("bastien@ppg.com");
        utilisateur.setInformation_supplementaire("c'est un sacré filou");
        utilisateur.setMotDePasse_Utilisateur("1234");
        utilisateur.setNom_Utilisateur("Housiaux");
        utilisateur.setPrenom_Utilisateur("Bastien");

        utilisateurRepository.save(utilisateur);

        Utilisateur utilisateur2=new Utilisateur();
        utilisateur2.setPseudo_Utilisateur("toto");
        utilisateur2.setEmail_Utilisateur("thomas@ppg.com");
        utilisateur2.setInformation_supplementaire("il aime pas les champignons");
        utilisateur2.setMotDePasse_Utilisateur("1234");
        utilisateur2.setNom_Utilisateur("Wattercamps");
        utilisateur2.setPrenom_Utilisateur("Thomas");

        utilisateurRepository.save(utilisateur2);

        Utilisateur utilisateur3=new Utilisateur();
        utilisateur3.setPseudo_Utilisateur("dada");
        utilisateur3.setEmail_Utilisateur("damien@ppg.com");
        utilisateur3.setInformation_supplementaire("il a 32 ans et deux enfants");
        utilisateur3.setMotDePasse_Utilisateur("1234");
        utilisateur3.setNom_Utilisateur("Fricot");
        utilisateur3.setPrenom_Utilisateur("Damien");

        utilisateurRepository.save(utilisateur3);

    }
}

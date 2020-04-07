package be.technobel.ucmppg;

import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class CucumberConfig {

    public UtilisateurEntity utilisateur(String utilisateurRegex)
    {
        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        Pattern pattern = Pattern.compile("mail:(?<mail>(.*)),pseudo:(?<pseudo>(.*)),motDePasse:(?<motDePasse>(.*))");
        Matcher matcher = pattern.matcher(utilisateurRegex);
        if (matcher.find())
        {
            utilisateurEntity.setEmailUtilisateur(matcher.group("mail"));
            utilisateurEntity.setPseudoUtilisateur(matcher.group("pseudo"));
            utilisateurEntity.setMotDePasseUtilisateur(matcher.group("motDePasse"));

        } else utilisateurEntity = null ;

        return utilisateurEntity;
    }
}

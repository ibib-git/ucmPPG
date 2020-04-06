package be.technobel.ucmppg.bl.service.utilisateur;

import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;
import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class RecuperationUtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public UtilisateurDetailsDTO connexionUtilisateur (String mail, String motDePasse)
    {
        Optional<UtilisateurEntity> utilisateurEntity = utilisateurRepository.findByEmailUtilisateurAndMotDePasseUtilisateur(mail,motDePasse);

        return utilisateurEntity.map(UtilisateurDetailsDTO::new).orElse(null);
    }

    public UtilisateurEntity recupererUtilisateur(long  id){
        UtilisateurEntity utilisateur = null;
        Optional<UtilisateurEntity> optimalutilisateurEntity = utilisateurRepository.findById(id);
        if(optimalutilisateurEntity.isPresent()) {
            utilisateur = optimalutilisateurEntity.get();
        }

        return utilisateur;
    }
}

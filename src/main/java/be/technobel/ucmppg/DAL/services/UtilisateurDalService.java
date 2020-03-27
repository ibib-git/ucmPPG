package be.technobel.ucmppg.DAL.services;

import be.technobel.ucmppg.DAL.Models.UtilisateurEntity;
import be.technobel.ucmppg.DAL.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurDalService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurDalService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public UtilisateurEntity save (UtilisateurEntity utilisateur){
        return utilisateurRepository.save(utilisateur);
    }

    public Optional<UtilisateurEntity> findByEmailAndMotDePasse (String email, String motDePasse)
    {
        return utilisateurRepository.findByEmailAndMotDePasse(email,motDePasse);
    }
}

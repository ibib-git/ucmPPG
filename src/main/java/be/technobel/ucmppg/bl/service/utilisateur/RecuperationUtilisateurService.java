package be.technobel.ucmppg.bl.service.utilisateur;

import be.technobel.ucmppg.Exception.ErrorServiceException;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurAuthentificationDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurConnexionDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;
import be.technobel.ucmppg.configuration.JwtTokenProvider;
import be.technobel.ucmppg.dal.entities.RoleGestionEntity;
import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecuperationUtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public UtilisateurDetailsDTO connexionUtilisateur (String mail) throws ErrorServiceException
    {
        Optional<UtilisateurEntity> utilisateurEntity = utilisateurRepository.findByEmailUtilisateur(mail);

        if (utilisateurEntity.isPresent()){
            return new UtilisateurDetailsDTO(utilisateurEntity.get());
        } else throw new ErrorServiceException("Utilisateur","Email incorrect");

    }

    public UtilisateurDTO recupererUtilisateur(long id) throws ErrorServiceException {

        Optional<UtilisateurEntity> optionalUtilisateurEntity = utilisateurRepository.findById(id);

        if (optionalUtilisateurEntity.isPresent())
        {

            return new UtilisateurDTO(optionalUtilisateurEntity.get());
        } else throw new ErrorServiceException("Utilisateur","Erreur de chargement de l utilisateur");
    }

    public List<String> getAllRoleGestionUtilisateur(String email) throws ErrorServiceException {
        return this.utilisateurRepository.findByEmailUtilisateur(email).orElseThrow(() -> new ErrorServiceException("Utilisateur","Erreur de chargement de l utilisateur")).getRoles().stream().map(RoleGestionEntity::getAuthority).collect(Collectors.toList());
    }

    public UtilisateurAuthentificationDTO authentificationUtilisateur(UtilisateurConnexionDTO utilisateurConnexionDTO) throws ErrorServiceException {

        try{
            String email = utilisateurConnexionDTO.getMail();
            // c est lui qui se charge de vérifier si email et mot de passe sont correct (c est lui qui decrypt le password)
            // en cas d erreur il renvoit une authentificationException
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,(utilisateurConnexionDTO.getMotDePasse())));

            UtilisateurDetailsDTO utilisateurDetailsDTO = connexionUtilisateur(utilisateurConnexionDTO.getMail());

            UtilisateurAuthentificationDTO authentificationDTO = new UtilisateurAuthentificationDTO(utilisateurDetailsDTO);
            authentificationDTO.setToken(jwtTokenProvider.createToken(email,getAllRoleGestionUtilisateur(email),utilisateurDetailsDTO));
            return authentificationDTO;
        } catch (AuthenticationException | ErrorServiceException e)
        {
            throw new ErrorServiceException("Connexion","Email ou mot de passe incorrect");
        }
    }

    public String refreshTokenUtilisateur(String token){

        return jwtTokenProvider.actualisationToken(token);

    }

}

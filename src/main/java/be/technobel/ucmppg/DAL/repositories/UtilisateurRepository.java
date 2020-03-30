package be.technobel.ucmppg.DAL.repositories;

import be.technobel.ucmppg.DAL.Models.UtilisateurEntityFromPast;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends CrudRepository<UtilisateurEntityFromPast,Long> {

    Optional<UtilisateurEntityFromPast> findByEmailAndMotDePasse(String email, String motDePasse);
}

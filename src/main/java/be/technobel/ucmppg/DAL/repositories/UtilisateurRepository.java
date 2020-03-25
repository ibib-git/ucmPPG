package be.technobel.ucmppg.DAL.repositories;

import be.technobel.ucmppg.DAL.Models.UtilisateurEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends CrudRepository<UtilisateurEntity,Long> {

    Optional<UtilisateurEntity> findByEmailAndMotDePasse(String email, String motDePasse);
}

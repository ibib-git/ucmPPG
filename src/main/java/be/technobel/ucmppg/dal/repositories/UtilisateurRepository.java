package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends CrudRepository<UtilisateurEntity,Long> {

    Optional<UtilisateurEntity> findByEmailAndMotDePasse(String email, String motDePasse);
    Optional<UtilisateurEntity> findByEmail(String mail);
  
}

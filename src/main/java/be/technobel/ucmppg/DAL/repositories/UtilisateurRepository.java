package be.technobel.ucmppg.DAL.repositories;

import be.technobel.ucmppg.DAL.Models.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur,Long> {
}

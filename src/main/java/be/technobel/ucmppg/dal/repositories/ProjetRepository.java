package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.ProjetEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjetRepository extends CrudRepository<ProjetEntity, Long> {

    Optional<ProjetEntity> findByIdProjet(Long id);
}

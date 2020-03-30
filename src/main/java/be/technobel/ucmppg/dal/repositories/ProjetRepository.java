package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.ProjetEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepository extends CrudRepository<ProjetEntity, Long> {
}

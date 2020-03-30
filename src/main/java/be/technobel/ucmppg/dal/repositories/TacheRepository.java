package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.TacheEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends CrudRepository<TacheEntity,Long> {
}

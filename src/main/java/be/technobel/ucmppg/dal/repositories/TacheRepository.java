package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.TacheEntity;
import org.springframework.data.repository.CrudRepository;

public interface TacheRepository extends CrudRepository<TacheEntity,Long> {
}

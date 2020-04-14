package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.HistoriqueTacheEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueTacheRepository extends CrudRepository<HistoriqueTacheEntity,Long> {
}

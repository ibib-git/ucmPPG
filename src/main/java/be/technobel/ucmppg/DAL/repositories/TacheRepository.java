package be.technobel.ucmppg.DAL.repositories;

import be.technobel.ucmppg.DAL.Models.TacheEntity;
import org.springframework.data.repository.CrudRepository;

public interface TacheRepository extends CrudRepository<TacheEntity,Long> {
}

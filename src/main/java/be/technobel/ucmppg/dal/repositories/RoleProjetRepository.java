package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.RoleProjetEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleProjetRepository extends CrudRepository<RoleProjetEntity,Long> {
}

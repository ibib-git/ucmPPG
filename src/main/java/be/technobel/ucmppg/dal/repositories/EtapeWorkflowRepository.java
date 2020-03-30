package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.EtapeWorkflowEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtapeWorkflowRepository extends CrudRepository<EtapeWorkflowEntity,Long> {
}

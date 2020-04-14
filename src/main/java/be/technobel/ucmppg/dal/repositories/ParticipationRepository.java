package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.ParticipationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationRepository extends CrudRepository<ParticipationEntity,Long> {
    boolean deleteByIdParticipation (Long idParticipation);
}

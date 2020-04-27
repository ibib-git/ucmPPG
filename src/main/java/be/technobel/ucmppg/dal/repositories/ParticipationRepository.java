package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.ParticipationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ParticipationRepository extends CrudRepository<ParticipationEntity,Long> {

    @Query(value = "select * from participation_projet "
            +" where utilisateur_participant_id_utilisateur = :idUtilisateur "
            +" and projet_participation_id_projet = :idProjet ",nativeQuery = true)
    Optional<ParticipationEntity> findByParticipationOfProjet(@Param("idUtilisateur") Long idUtilisateur,@Param("idProjet") Long idProjet);
   
    boolean deleteByIdParticipation (Long idParticipation);
}


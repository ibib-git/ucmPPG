package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.EtapeWorkflowEntity;
import be.technobel.ucmppg.dal.entities.ProjetEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.Set;


@Repository
public interface ProjetRepository extends CrudRepository<ProjetEntity, Long> {

    Optional<ProjetEntity> findByIdProjet(Long id);

    @Query
    Optional<Set<EtapeWorkflowEntity>> findEtapeWorkflowEntityProjetByIdProjet(Long idProjet);

    Optional<ProjetEntity> findByNomDeProjet(String nom);
}

package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.EtapeWorkflowEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtapeWorkflowRepository extends CrudRepository<EtapeWorkflowEntity,Long> {

    Optional<EtapeWorkflowEntity> findBynomEtapeWorkflow (String nomEtapeWorkflow);

    @Query(value ="select * from colonne_du_workflow"
            +" join colonne_du_workflow_taches"
            +" on colonne_du_workflow.id_etape_workflow = colonne_du_workflow_taches.etape_workflow_entity_id_etape_workflow"
            +" join tableau_de_tache"
            +" on tableau_de_tache.id_tache = colonne_du_workflow_taches.taches_id_tache"
            +" where tableau_de_tache.id_tache = :idTache ",nativeQuery = true)
    Optional<EtapeWorkflowEntity> findByTacheEntity(@Param("idTache") Long idTache);
}

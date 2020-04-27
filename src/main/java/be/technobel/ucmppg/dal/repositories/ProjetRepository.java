package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.EtapeWorkflowEntity;
import be.technobel.ucmppg.dal.entities.ProjetEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.Set;


@Repository
public interface ProjetRepository extends CrudRepository<ProjetEntity, Long> {

    Optional<ProjetEntity> findByIdProjet(Long id);
  
    @Query
    Optional<Set<EtapeWorkflowEntity>> findEtapeWorkflowEntityProjetByIdProjet(Long idProjet);

    Optional<ProjetEntity> findByNomDeProjet(String nom);
  
    @Query(value ="select * from TABLEAU_PROJET p" +
            " join TABLEAU_PROJET_ETAPE_WORKFLOWS e" +
            "   on e.PROJET_ENTITY_ID_PROJET = p.ID_PROJET" +
            " where e.ETAPE_WORKFLOWS_ID_ETAPE_WORKFLOW = :idEtape",
            nativeQuery = true)
    Optional<ProjetEntity> getProjetByEtapeWorkflows(@Param("idEtape") Long idEtape);

    @Query(value = "select p.ID_PROJET from TABLEAU_PROJET p" +
                    " join TABLEAU_PROJET_ETAPE_WORKFLOWS e" +
                    " on e.PROJET_ENTITY_ID_PROJET = p.ID_PROJET" +
                    " join COLONNE_DU_WORKFLOW_TACHES c" +
                    " on c.ETAPE_WORKFLOW_ENTITY_ID_ETAPE_WORKFLOW = e.ETAPE_WORKFLOWS_ID_ETAPE_WORKFLOW" +
                    " where c.TACHES_ID_TACHE = :idTache",
                nativeQuery = true)
    Long getIdProjetEntityByTacheParent(@Param("idTache") Long idTache);

    @Query(value = "select p.ID_PROJET from TABLEAU_PROJET p" +
            "   join TABLEAU_PROJET_ETAPE_WORKFLOWS e" +
            "       on e.PROJET_ENTITY_ID_PROJET = p.ID_PROJET" +
            "   join COLONNE_DU_WORKFLOW_TACHES c" +
            "       on c.ETAPE_WORKFLOW_ENTITY_ID_ETAPE_WORKFLOW = e.ETAPE_WORKFLOWS_ID_ETAPE_WORKFLOW" +
            "   join TABLEAU_DE_TACHE_TACHES_ENFANTS te" +
            "       on te.TACHE_ENTITY_ID_TACHE = c.TACHES_ID_TACHE" +
            "   where te.TACHES_ENFANTS_ID_TACHE = :idTache",
            nativeQuery =  true)
    Long getIdProjetEntityByTacheEnfant(@Param("idTache") Long idTache);
}

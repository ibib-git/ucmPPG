package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.ProjetEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjetRepository extends CrudRepository<ProjetEntity, Long> {

    Optional<ProjetEntity> findByIdProjet(Long id);

    @Query(value ="select * from TABLEAU_PROJET p\n" +
            "join TABLEAU_PROJET_ETAPE_WORKFLOWS e\n" +
            "on e.PROJET_ENTITY_ID_PROJET = p.ID_PROJET\n" +
            "where e.ETAPE_WORKFLOWS_ID_ETAPE_WORKFLOW = ?1",
            nativeQuery = true)
    Optional<ProjetEntity> getProjetByEtapeWorkflows(Long idEtape);

    @Query(value = "select p.ID_PROJET from TABLEAU_PROJET p\n" +
                    " join TABLEAU_PROJET_ETAPE_WORKFLOWS e\n" +
                    " on e.PROJET_ENTITY_ID_PROJET = p.ID_PROJET\n" +
                    " join COLONNE_DU_WORKFLOW_TACHES c\n" +
                    " on c.ETAPE_WORKFLOW_ENTITY_ID_ETAPE_WORKFLOW = e.ETAPE_WORKFLOWS_ID_ETAPE_WORKFLOW\n" +
                    " where c.TACHES_ID_TACHE = ?1",
                nativeQuery = true)
    Long getIdProjetEntityByTacheParent(Long idTache);

    @Query(value = "select p.ID_PROJET from TABLEAU_PROJET p\n" +
            "join TABLEAU_PROJET_ETAPE_WORKFLOWS e\n" +
            "on e.PROJET_ENTITY_ID_PROJET = p.ID_PROJET\n" +
            "join COLONNE_DU_WORKFLOW_TACHES c\n" +
            "on c.ETAPE_WORKFLOW_ENTITY_ID_ETAPE_WORKFLOW = e.ETAPE_WORKFLOWS_ID_ETAPE_WORKFLOW\n" +
            "join TABLEAU_DE_TACHE_TACHES_ENFANTS te\n" +
            "on te.TACHE_ENTITY_ID_TACHE = c.TACHES_ID_TACHE\n" +
            "where te.TACHES_ENFANTS_ID_TACHE = ?1",
            nativeQuery =  true)
    Long getIdProjetEntityByTacheEnfant(Long idTache);
}

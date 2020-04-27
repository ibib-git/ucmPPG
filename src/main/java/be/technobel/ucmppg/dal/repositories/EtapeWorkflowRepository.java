package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.EtapeWorkflowEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.Optional;

@Repository
public interface EtapeWorkflowRepository extends CrudRepository<EtapeWorkflowEntity,Long> {

    @Query (value = "select c.ID_ETAPE_WORKFLOW from COLONNE_DU_WORKFLOW c" +
            "        where c.ID_ETAPE_WORKFLOW in\n" +
            "            (select max(c.NUMERO_ORDRE) from COLONNE_DU_WORKFLOW c" +
            "                    join TABLEAU_PROJET_ETAPE_WORKFLOWS p" +
            "                        on c.ID_ETAPE_WORKFLOW = p.ETAPE_WORKFLOWS_ID_ETAPE_WORKFLOW" +
            "                where p.PROJET_ENTITY_ID_PROJET = :idProjet)",
            nativeQuery = true)
    Long getIdDernièreEtapeByIdProjet (@Param("idProjet") Long idProjet);

    @Query(value = "select c.ID_ETAPE_WORKFLOW, CONSTRAINTE, DESCRIPTION_DE_LA_COLONNE, PRENABLE, NOM_DE_LA_COLONNE, NUMERO_ORDRE"+
            " from COLONNE_DU_WORKFLOW c" +
            "                        join COLONNE_DU_WORKFLOW_TACHES tc" +
            "                            on c.ID_ETAPE_WORKFLOW = tc.ETAPE_WORKFLOW_ENTITY_ID_ETAPE_WORKFLOW" +
            "where tc.TACHES_ID_TACHE = :idTache",
                nativeQuery = true)
    EtapeWorkflowEntity getEtapeByIdTache (@Param("idTache") Long idTache);

    @Query (value = "select c.ID_ETAPE_WORKFLOW, CONSTRAINTE, DESCRIPTION_DE_LA_COLONNE, PRENABLE, NOM_DE_LA_COLONNE, NUMERO_ORDRE"+
            " from COLONNE_DU_WORKFLOW c" +
            "        left join COLONNE_DU_WORKFLOW_TACHES tc" +
            "            on c.ID_ETAPE_WORKFLOW = tc.ETAPE_WORKFLOW_ENTITY_ID_ETAPE_WORKFLOW" +
            "         left join TABLEAU_DE_TACHE_TACHES_ENFANTS te" +
            "            on tc.TACHES_ID_TACHE = te.TACHE_ENTITY_ID_TACHE" +
            "        where te.TACHES_ENFANTS_ID_TACHE = :idTacheEnfant",
                nativeQuery = true)
    EtapeWorkflowEntity getEtapeByIdTacheEnfant (@Param("idTacheEnfant") Long idTacheEnfant);

    @Query (value = "select cw.ID_ETAPE_WORKFLOW, CONSTRAINTE, DESCRIPTION_DE_LA_COLONNE, PRENABLE, NOM_DE_LA_COLONNE, NUMERO_ORDRE" +
            "    from COLONNE_DU_WORKFLOW cw" +
            "        join TABLEAU_PROJET_ETAPE_WORKFLOWS TPEW" +
            "            on cw.ID_ETAPE_WORKFLOW = TPEW.ETAPE_WORKFLOWS_ID_ETAPE_WORKFLOW" +
            "        where TPEW.PROJET_ENTITY_ID_PROJET = :idProjet and cw.NUMERO_ORDRE > :numOrdreActu" +
            "        order by cw.NUMERO_ORDRE" +
            "        limit 1 ",
                    nativeQuery = true)
    Optional<EtapeWorkflowEntity> getNextEtapeByIdProjetAndEtape (@Param("idProjet") Long idProjet,@Param("numOrdreActu") Integer numOrdreActu);

    Optional<EtapeWorkflowEntity> findBynomEtapeWorkflow (String nomEtapeWorkflow);

    @Query(value ="select * from colonne_du_workflow"
            +" join colonne_du_workflow_taches"
            +" on colonne_du_workflow.id_etape_workflow = colonne_du_workflow_taches.etape_workflow_entity_id_etape_workflow"
            +" join tableau_de_tache"
            +" on tableau_de_tache.id_tache = colonne_du_workflow_taches.taches_id_tache"
            +" where tableau_de_tache.id_tache = :idTache ",nativeQuery = true)
    Optional<EtapeWorkflowEntity> findByTacheEntity(@Param("idTache") Long idTache);


}

package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.EtapeWorkflowEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtapeWorkflowRepository extends CrudRepository<EtapeWorkflowEntity,Long> {

    @Query (value = "select c.ID_ETAPE_WORKFLOW from COLONNE_DU_WORKFLOW c" +
            "        where c.ID_ETAPE_WORKFLOW in\n" +
            "            (select max(c.NUMERO_ORDRE) from COLONNE_DU_WORKFLOW c" +
            "                    join TABLEAU_PROJET_ETAPE_WORKFLOWS p" +
            "                        on c.ID_ETAPE_WORKFLOW = p.ETAPE_WORKFLOWS_ID_ETAPE_WORKFLOW" +
            "                where p.PROJET_ENTITY_ID_PROJET = ?1)",
            nativeQuery = true)
    Long getIdDernièreEtapeByIdProjet (Long idProjet);

    @Query(value = "select c.ID_ETAPE_WORKFLOW, CONSTRAINTE, DESCRIPTION_DE_LA_COLONNE, PRENABLE, NOM_DE_LA_COLONNE, NUMERO_ORDRE\n"+
            " from COLONNE_DU_WORKFLOW c\n" +
            "                        join COLONNE_DU_WORKFLOW_TACHES tc\n" +
            "                            on c.ID_ETAPE_WORKFLOW = tc.ETAPE_WORKFLOW_ENTITY_ID_ETAPE_WORKFLOW\n" +
            "where tc.TACHES_ID_TACHE = ?1",
                nativeQuery = true)
    EtapeWorkflowEntity getEtapeByIdTache (Long idTache);

    @Query (value = "select c.ID_ETAPE_WORKFLOW, CONSTRAINTE, DESCRIPTION_DE_LA_COLONNE, PRENABLE, NOM_DE_LA_COLONNE, NUMERO_ORDRE\n"+
            " from COLONNE_DU_WORKFLOW c\n" +
            "        left join COLONNE_DU_WORKFLOW_TACHES tc\n" +
            "            on c.ID_ETAPE_WORKFLOW = tc.ETAPE_WORKFLOW_ENTITY_ID_ETAPE_WORKFLOW\n" +
            "         left join TABLEAU_DE_TACHE_TACHES_ENFANTS te\n" +
            "            on tc.TACHES_ID_TACHE = te.TACHE_ENTITY_ID_TACHE\n" +
            "        where te.TACHES_ENFANTS_ID_TACHE = ?1",
                nativeQuery = true)
    EtapeWorkflowEntity getEtapeByIdTacheEnfant (Long idTacheEnfant);

    @Query (value = "select cw.ID_ETAPE_WORKFLOW, CONSTRAINTE, DESCRIPTION_DE_LA_COLONNE, PRENABLE, NOM_DE_LA_COLONNE, NUMERO_ORDRE\n" +
            "    from COLONNE_DU_WORKFLOW cw\n" +
            "        join TABLEAU_PROJET_ETAPE_WORKFLOWS TPEW\n" +
            "            on cw.ID_ETAPE_WORKFLOW = TPEW.ETAPE_WORKFLOWS_ID_ETAPE_WORKFLOW\n" +
            "        where TPEW.PROJET_ENTITY_ID_PROJET = ?1 and cw.NUMERO_ORDRE > ?2\n" +
            "        order by cw.NUMERO_ORDRE\n" +
            "        limit 1 ",
                    nativeQuery = true)
    Optional<EtapeWorkflowEntity> getNextEtapeByIdProjetAndEtape (Long idProjet, Integer numOrdreActu);

}

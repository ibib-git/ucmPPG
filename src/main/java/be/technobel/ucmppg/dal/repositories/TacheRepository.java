package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.TacheEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TacheRepository extends CrudRepository<TacheEntity,Long> {

    @Query(value = "select ID_TACHE from TABLEAU_DE_TACHE t" +
                    " join TABLEAU_DE_TACHE_TACHES_ENFANTS te" +
                    " on t.ID_TACHE = te.TACHE_ENTITY_ID_TACHE" +
                    " where te.TACHES_ENFANTS_ID_TACHE = :idEnfant",
                    nativeQuery = true)
    Optional<Long> getIdTacheParent (@Param("idEnfant") Long idEnfant);

    @Query(value = "select * from TABLEAU_DE_TACHE t" +
            "    join TACHES_PRECEDENTES tp" +
            "        on tp.TACHE_PRECEDENTE = t.ID_TACHE" +
            "    where tp.TACHE_SUIVANTE = :idTache",
                nativeQuery = true)
    Optional<List<TacheEntity>> getAllTachePrecedente (@Param("idTache") Long idTache);


    @Query(value = "select * from TABLEAU_DE_TACHE t" +
            "    join TABLEAU_DE_TACHE_TACHES_ENFANTS te" +
            "        on t.ID_TACHE = te.TACHES_ENFANTS_ID_TACHE" +
            "    where te.TACHE_ENTITY_ID_TACHE = :idTache",
            nativeQuery = true)
    Optional<List<TacheEntity>> getAllTacheEnfant (@Param("idTache") Long idTache);

    @Query(value =" select * from tableau_de_tache"
            +" join colonne_du_workflow_taches"
            +" on tableau_de_tache.id_tache = colonne_du_workflow_taches.taches_id_tache"
            +" join colonne_du_workflow"
            +" on colonne_du_workflow.id_etape_workflow = colonne_du_workflow_taches.etape_workflow_entity_id_etape_workflow"
            +" join tableau_projet_etape_workflows"
            +" on tableau_projet_etape_workflows.etape_workflows_id_etape_workflow = colonne_du_workflow_taches.etape_workflow_entity_id_etape_workflow"
            +" join tableau_projet"
            +" on tableau_projet.id_projet = tableau_projet_etape_workflows.projet_entity_id_projet"
            +" join tableau_utilisateur"
            +" on tableau_utilisateur.id_utilisateur = tableau_de_tache.utilisateur_tache_id_utilisateur"
            +" where tableau_projet.id_projet = :idProjet"
            +" and tableau_utilisateur.id_utilisateur = :idUtilisateur", nativeQuery = true)
    Optional<List<TacheEntity>> findByProjetAndUtilisateur(@Param("idProjet") Long idProjet, @Param("idUtilisateur") Long idUtilisateur);

    Optional<TacheEntity> findByNomTache(String nomTache);


}

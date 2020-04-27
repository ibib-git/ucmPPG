package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.HistoriqueTacheEntity;
import be.technobel.ucmppg.dal.entities.TacheEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoriqueTacheRepository extends CrudRepository<HistoriqueTacheEntity,Long> {

    @Query(value = "select * from HISTORIQUE_DE_TACHE h" +
            "        where h.TACHE_HISTORIQUE_ID_TACHE in" +
            "                (select t.ID_TACHE from TABLEAU_DE_TACHE t" +
            "                  join TACHES_PRECEDENTES tp" +
            "                       on tp.TACHE_PRECEDENTE = t.ID_TACHE" +
            "                where tp.TACHE_SUIVANTE = :idTache)",
            nativeQuery = true)
    Optional<List<HistoriqueTacheEntity>> getAllTachePrecedenteValidee (@Param("idTache") Long idTache);

    @Query(value = "select * from HISTORIQUE_DE_TACHE h" +
            "    where h.TACHE_HISTORIQUE_ID_TACHE in" +
            "        (select t.ID_TACHE from TABLEAU_DE_TACHE t" +
            "            join TABLEAU_DE_TACHE_TACHES_ENFANTS te" +
            "                on t.ID_TACHE = te.TACHES_ENFANTS_ID_TACHE" +
            "            where te.TACHE_ENTITY_ID_TACHE = :idTache)",
            nativeQuery = true)
    Optional<List<HistoriqueTacheEntity>> getAllTacheEnfantValidee (@Param("idTache") Long idTache);

    @Query(value = "select * from historique_de_tache "
            +" where tache_historique_id_tache = :idTache "
            +" and utilisateur_tache_historique_id_utilisateur = :idUtilisateur ",nativeQuery = true)
    Optional<HistoriqueTacheEntity> findByIdTacheAndIdUtilisateur(@Param("idTache")Long idTache, @Param("idUtilisateur") Long idUtilisateur);

}

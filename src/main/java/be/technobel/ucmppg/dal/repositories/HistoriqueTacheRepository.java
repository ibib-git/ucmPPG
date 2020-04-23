package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.HistoriqueTacheEntity;
import be.technobel.ucmppg.dal.entities.TacheEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoriqueTacheRepository extends CrudRepository<HistoriqueTacheEntity,Long> {

    @Query(value = "select * from HISTORIQUE_DE_TACHE h\n" +
            "        where h.TACHE_HISTORIQUE_ID_TACHE in\n" +
            "                (select t.ID_TACHE from TABLEAU_DE_TACHE t\n" +
            "                  join TACHES_PRECEDENTES tp\n" +
            "                       on tp.TACHE_PRECEDENTE = t.ID_TACHE\n" +
            "                where tp.TACHE_SUIVANTE = ?1)",
            nativeQuery = true)
    Optional<List<HistoriqueTacheEntity>> getAllTachePrecedenteValidee (Long idTache);

    @Query(value = "select * from HISTORIQUE_DE_TACHE h\n" +
            "    where h.TACHE_HISTORIQUE_ID_TACHE in\n" +
            "        (select t.ID_TACHE from TABLEAU_DE_TACHE t\n" +
            "            join TABLEAU_DE_TACHE_TACHES_ENFANTS te\n" +
            "                on t.ID_TACHE = te.TACHES_ENFANTS_ID_TACHE\n" +
            "            where te.TACHE_ENTITY_ID_TACHE = ?1)",
            nativeQuery = true)
    Optional<List<HistoriqueTacheEntity>> getAllTacheEnfantValidee (Long idTache);
}

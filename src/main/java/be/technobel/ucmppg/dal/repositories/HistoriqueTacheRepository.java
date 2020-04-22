package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.HistoriqueTacheEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoriqueTacheRepository extends CrudRepository<HistoriqueTacheEntity,Long> {

    @Query(value = "select * from historique_de_tache "
            +" where tache_historique_id_tache = :idTache "
            +" and utilisateur_tache_historique_id_utilisateur = :idUtilisateur ",nativeQuery = true)
    Optional<HistoriqueTacheEntity> findByIdTacheAndIdUtilisateur(@Param("idTache")Long idTache, @Param("idUtilisateur") Long idUtilisateur);

}

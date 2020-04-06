package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.RoleProjetEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleProjetRepository extends CrudRepository<RoleProjetEntity,Long> {
    @Query("SELECT role FROM ProjetEntity projet  JOIN RoleProjetEntity role WHERE projet.idProjet=?1 and role.nomDeRole=?2")
    public Optional<RoleProjetEntity> getRoleSurProjetByNomDeRole(long idProjet, String nomDeRole);

}

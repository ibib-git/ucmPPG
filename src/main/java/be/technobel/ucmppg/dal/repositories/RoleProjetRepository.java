package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.RoleProjetEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleProjetRepository extends CrudRepository<RoleProjetEntity,Long> {
//    @Query("SELECT roleUtilisateur FROM ProjetEntity projet  JOIN RoleProjetEntity roleUtilisateur WHERE projet.idProjet=?1 and roleUtilisateur.nomDeRole=?2")

    @Query(value = "SELECT * FROM role_dans_projet " +
            "JOIN tableau_projet_roles_projet " +
            "ON tableau_projet_roles_projet.roles_projet_id_role = role_dans_projet.id_role " +
            "WHERE tableau_projet_roles_projet.projet_entity_id_projet=?1 " +
            "AND role_dans_projet.nom_de_role LIKE ?2 ",nativeQuery = true)
    public Optional<RoleProjetEntity> getRoleSurProjetByNomDeRole(long idProjet, String nomDeRole);

}

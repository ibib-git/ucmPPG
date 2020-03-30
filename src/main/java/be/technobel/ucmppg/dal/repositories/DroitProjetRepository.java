package be.technobel.ucmppg.dal.repositories;

import be.technobel.ucmppg.dal.entities.DroitProjetEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroitProjetRepository extends CrudRepository<DroitProjetEntity,Long> {
    DroitProjetEntity findDroitProjetByNomDroit(String nomDroit);
}

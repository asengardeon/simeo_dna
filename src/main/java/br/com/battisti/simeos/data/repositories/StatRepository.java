package br.com.battisti.simeos.data.repositories;

import br.com.battisti.simeos.data.entities.StatEntity;
import org.springframework.data.repository.CrudRepository;

public interface StatRepository extends CrudRepository<StatEntity, String> {

    StatEntity findByKey(String key);

}

package br.com.wep.app.model.Repos;

import br.com.wep.app.model.Entities.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepo extends CrudRepository<Event, Integer>  {


}

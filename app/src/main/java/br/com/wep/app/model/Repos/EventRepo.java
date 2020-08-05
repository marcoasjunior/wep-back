package br.com.wep.app.model.Repos;

import br.com.wep.app.model.Entities.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepo extends CrudRepository<Event, Integer>  {

    public List<Event> getEventByPrivated(boolean privated);

}

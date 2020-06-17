package br.com.wep.app.Controllers;

import br.com.wep.app.model.Entities.Event;
import br.com.wep.app.model.Repos.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/event")
public class EventController {

    @Autowired
    private EventRepo repo;

    @GetMapping
    public List<Event> getEvent(){
        List<Event> events = (List<Event>) repo.findAll();
        return events;
    }

    //Registrar evento
    @PostMapping
    public Event registerEvent(Event event){
        return repo.save(event);
    }

    //Deletar evento
    @DeleteMapping(path = "/{event_id}")
    public boolean deleteEvent(@PathVariable int event_id){
        try{
            repo.deleteById(event_id);
            return true;
        }catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}

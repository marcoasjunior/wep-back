package br.com.wep.app.Controllers;

import br.com.wep.app.model.Entities.Event;
import br.com.wep.app.model.Repos.EventRepo;
import br.com.wep.app.model.Repos.UserRepo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.net.URL;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/event")
public class EventController {

    @Autowired
    private EventRepo repo;
    private UserRepo userRepo;

    @GetMapping(path = "/list")
    public List<Event> getEvents(){
        List<Event> events = (List<Event>) repo.findAll();
        return events;
    }

    //Registrar evento
    @PostMapping
    public Event registerEvent(@RequestBody Event event){
        try {
            return repo.save(event);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    //Update Event
    @PutMapping(path = "/{eventID}")
    public Event updateEvent(@RequestBody Event newEvent,  @PathVariable int eventID){
        try {

            Event eventId = repo.findById(eventID).get();

            eventId.setEventeDate(newEvent.getEventeDate());

            return repo.save(eventId);

//            comment.setComment(newComment.getComment());


        }catch (Exception exc){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Evento não alterado ", exc);
        }
    }


    //Deletar evento
    @DeleteMapping(path = "/{event_id}")
    public boolean deleteEvent(@PathVariable(name = "event_id") int eventID,
                               @RequestHeader String Authentication){
        try {
            repo.deleteById(eventID);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //get event ID
    @GetMapping(path = "/{eventID}")
    public Event getEventById(@PathVariable(name = "eventID") int eventID){
        try {
            Event event = repo.findById(eventID).get();
//            Event eventFound = event.get();

            return event;

        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    //TODO
    //Fazer um get com parametro de evento privado ou publico

}

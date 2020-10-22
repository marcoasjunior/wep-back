package br.com.wep.app.Controllers;

import br.com.wep.app.config.TokenService;
import br.com.wep.app.model.Entities.Event;
import br.com.wep.app.model.Entities.User;
import br.com.wep.app.model.Repos.EventRepo;
import br.com.wep.app.model.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/event")
public class EventController {

    @Autowired
    private EventRepo repo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping(path = "/list")
    public List<Event> getEvents(){
        List<Event> events = (List<Event>) repo.findAll();
        return events;
    }

    //Registrar evento
    @PostMapping(path = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Event registerEvent(@RequestBody Event event){
        try {
//            return repo.save(event);
            User user = userRepo.findById(event.getUser().getId()).get();
            event.setUser(user);

            return repo.save(event);

        }catch (Exception exc){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Evento não Criado ", exc);
        }
    }

    //Update Event
    @PutMapping(path = "/{eventID}")
    public Event updateEvent(@RequestBody Event newEvent,  @PathVariable int eventID){
        try {

            Event eventId = repo.findById(eventID).get();


            eventId.setTitle(newEvent.getTitle());
            eventId.setDescription(newEvent.getDescription());
            eventId.setAdress(newEvent.getAdress());
            eventId.setImg(newEvent.getImg());
            eventId.setPrivated(newEvent.getPrivated());
            eventId.setEventDate(newEvent.getEventDate());
            eventId.setLatitude(newEvent.getLatitude());
            eventId.setLongitude(newEvent.getLongitude());

            return repo.save(eventId);


        }catch (Exception exc){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Evento não alterado ", exc);
        }
    }

    // Add like
    @PutMapping(path = "/{eventID}")
    public Event like(@RequestBody User user,  @PathVariable int eventID){
        try {

            Event event = repo.findById(eventID).get();

            List<User> liked = event.getLiked();
            liked.add(user);

            Integer likes = event.getLikes();
            Integer newLikes = likes + 1;


            event.setLiked(liked);
            event.setLikes(newLikes);

            return repo.save(event);


        }   catch (Exception exc){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não alterado ", exc);
        }
    }

    @PutMapping(path = "/{eventID}")
    public Event unlike(@RequestBody User user,  @PathVariable int eventID){
        try {

            Event event = repo.findById(eventID).get();

            List<User> liked = event.getLiked();
            liked.remove(user);

            Integer likes = event.getLikes();
            Integer newLikes = likes - 1;


            event.setLiked(liked);
            event.setLikes(newLikes);

            return repo.save(event);


        }   catch (Exception exc){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não alterado ", exc);
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


    //Fazer um get com parametro de evento privado ou publico
    @GetMapping(path = "/event")
    public List<Event>getEventByPrivateParams(@RequestParam (name = "privated") boolean privated){
        try {

            List<Event> events = repo.getEventByPrivated(privated);

//            Event event = repo.findById(privated).get();
//            Event eventFound = event.get();

//            System.out.println(event);

            return events;

        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @GetMapping(path = "/user")
    @ResponseBody
    public List<Event> getEventByUser(@RequestHeader String Authentication) {
        try{
            String token = TokenService.decodeToken(Authentication).getSubject();
            User user = userRepo.getUserByEmail(token);

            return user.getMyEvents();
        }catch (Exception e){
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}

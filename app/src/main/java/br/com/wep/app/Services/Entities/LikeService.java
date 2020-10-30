package br.com.wep.app.Services.Entities;

import br.com.wep.app.exceptions.InvalidUserEventException;
import br.com.wep.app.exceptions.LikeNotFound;
import br.com.wep.app.model.Entities.Event;
import br.com.wep.app.model.Entities.Like;
import br.com.wep.app.model.Entities.User;
import br.com.wep.app.model.Repos.EventRepo;
import br.com.wep.app.model.Repos.LikeRepo;
import br.com.wep.app.model.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private LikeRepo likeRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EventRepo eventRepo;

    public List<Like> index(){
        return (List<Like>) likeRepo.findAll();
    };

    public Like save(final int userId, final int eventId) {

        Event event = eventRepo.findById(eventId).get();
        User user = userRepo.findById(userId).get();

        int eventLikes = event.getLikes();
        int newLikes =  eventLikes + 1;

        event.setLikes(newLikes);

        Like newLike = new Like(event, user);

        likeRepo.save(newLike);
        eventRepo.save(event);

        return newLike;

    };

    public Like delete(final int userId, final int eventId) throws InvalidUserEventException, LikeNotFound {

        Optional<User> user = userRepo.findById(userId);
        Optional<Event> event = eventRepo.findById(eventId);

        if (user.isEmpty() && event.isEmpty()) throw new InvalidUserEventException("Erro ao deletar");

        Optional<Like> like = likeRepo.findLikebyEventAndUser(userId, eventId);

        if (like.isEmpty()) throw new LikeNotFound("Erro ao deletar");

        likeRepo.delete(like.get());

        Event realEvent = event.get();

        int eventLikes = realEvent.getLikes();
        int newLikes =  eventLikes - 1;
        realEvent.setLikes(newLikes);
        eventRepo.save(realEvent);

        return like.get();

    };


}

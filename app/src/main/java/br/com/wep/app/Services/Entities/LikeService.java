package br.com.wep.app.Services.Entities;

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
import java.util.stream.Collectors;

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

    public boolean delete(int userId, int eventId){

        Optional<User> user = userRepo.findById(userId);
        Optional<Event> event = eventRepo.findById(eventId);

        List<Like> likes = (List<Like>) likeRepo.findAll();

        List<User> check = likes.stream().filter(eLike -> eLike.getUser().equals(user)  eLike.getUser().equals(user) ).collect(Collectors.toList());

        Like like = check.get(0);

        likeRepo.delete(like);

        return true;


    };


}

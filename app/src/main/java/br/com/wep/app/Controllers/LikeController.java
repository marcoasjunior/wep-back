package br.com.wep.app.Controllers;

import br.com.wep.app.Services.Entities.LikeService;
import br.com.wep.app.model.Entities.Like;
import br.com.wep.app.model.Repos.EventRepo;
import br.com.wep.app.model.Repos.LikeRepo;
import br.com.wep.app.model.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/like")
public class LikeController {

    @Autowired
    private LikeService likeService;
    @Autowired
    private EventRepo eventRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private LikeRepo likeRepo;

    @GetMapping
    public List<Like> index() {
        return likeService.index();
    };

    @PostMapping(path = "/like/{userID}/{eventID}")
    public Like create(@PathVariable Integer eventID, @PathVariable Integer userID ) {
        try{

            return likeService.save(userID, eventID);

        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    @DeleteMapping(path = "/like/{userID}/{eventID}")
    public ResponseEntity<?> delete(@PathVariable Integer eventID, @PathVariable Integer userID ) throws Exception {

        try {
            likeService.delete(eventID, userID);
            return new ResponseEntity<String>("Like Deleted", HttpStatus.OK);

        }  catch (Exception e) {
            return new ResponseEntity<String>("Unable delete", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

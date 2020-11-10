package br.com.wep.app.Controllers;

import br.com.wep.app.Services.Entities.CommentService;
import br.com.wep.app.config.TokenService;
import br.com.wep.app.model.Entities.Comment;
import br.com.wep.app.model.Entities.Event;
import br.com.wep.app.model.Entities.User;
import br.com.wep.app.model.Repos.CommentRepo;
import br.com.wep.app.model.Repos.EventRepo;
import br.com.wep.app.model.Repos.UserRepo;
import org.apache.tomcat.util.json.JSONParser;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.Authenticator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/comment")
public class CommentController {

    @Autowired
    private CommentRepo repo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EventRepo eventRepo;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TokenService tokenService;

    @GetMapping
    public List<Comment> index() {
        return commentService.index();
    };

    @PostMapping("/{event_id}")
    public Object create(
            @PathVariable(name = "event_id") int event_id,
            @RequestBody Comment comment,
            @RequestHeader String Authentication
    ){
        try{
            Event event = eventRepo.findById(event_id).get();
            String token = tokenService.decodeToken(Authentication).getSubject();
            User user = userRepo.getUserByEmail(token);

            Comment newComment = new Comment(comment.getComment(), event, user);

            return repo.save(newComment);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @CrossOrigin
    @DeleteMapping(path = "/{commentId}")
    public void delete(@PathVariable Integer commentId, @RequestHeader String Authentication) throws Exception {

        Boolean response = commentService.delete(commentId, Authentication);

        System.out.println(response);
    }

    ;

    @CrossOrigin
    @PutMapping(path = "/{commentId}")
    public Comment update(@RequestBody Comment newComment, @PathVariable int commentId) {

        try {

            Comment comment = repo.findById(commentId).get();

            comment.setComment(newComment.getComment());

            return repo.save(newComment);

        } catch (Exception exc) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Comentário não encontrado ", exc);

        }

    }

    ;

    //get comment ID
    
    @GetMapping(path = "/find/{commentId}")
    public Comment getCommentById(@PathVariable(name = "commentId") int commentId){

        try {
            Comment comment = repo.findById(commentId).get();

            return comment;

        } catch (Exception exc) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Comentário não encontrado ", exc);

        }
    };

}

package br.com.wep.app.Controllers;

import br.com.wep.app.model.Entities.Comment;
import br.com.wep.app.model.Entities.Event;
import br.com.wep.app.model.Entities.User;
import br.com.wep.app.model.Repos.CommentRepo;
import br.com.wep.app.model.Repos.EventRepo;
import br.com.wep.app.model.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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


    @GetMapping
    public List<Comment> index() {
        return (List<Comment>) repo.findAll();
    };

    @PostMapping
    public Comment create(@RequestBody Comment comment) {
        try{
            Event event = eventRepo.findById(comment.getEvent().getId()).get();
            User user = userRepo.findById(comment.getUser().getId()).get();

            comment.setEvent(event);
            comment.setUser(user);

            return repo.save(comment);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    };

    @DeleteMapping(path = "/{commentId}")
    public Boolean delete(@PathVariable Integer commentId) {

        try {

            repo.deleteById(commentId);
            return true;

        } catch (Exception exc) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Comentário não encontrado ", exc);

        }

    }

    ;

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

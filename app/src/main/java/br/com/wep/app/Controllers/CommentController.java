package br.com.wep.app.Controllers;

import br.com.wep.app.model.Entities.Comment;
import br.com.wep.app.model.Repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {

    @Autowired
    private CommentRepo repo;


    @GetMapping
    public List<Comment> index() {

        return (List<Comment>) repo.findAll();


    }

    ;

    @PostMapping
    public Comment create(@RequestBody Comment comment) {

        return repo.save(comment);

    }

    ;

    @DeleteMapping(path = "/{commentId}")
    public Boolean delete(@PathVariable Integer commentId) {

        try {

            repo.deleteById(commentId);
            return true;

        } catch (Exception err) {

            System.out.println("Erro ao deletar: " + err);
            return false;

        }


    }

    ;

    @PutMapping(path = "/{commentId}")
    public Object update(@RequestBody Comment newComment, @PathVariable int commentId) {

        try {

            return repo.findById(commentId).map(comment -> {

                comment.setComment(newComment.getComment());
                return repo.save(newComment);

            });

        } catch (Exception err) {

            System.out.println("Erro ao deletar: " + err);
            return false;

        }

    }

    ;


}

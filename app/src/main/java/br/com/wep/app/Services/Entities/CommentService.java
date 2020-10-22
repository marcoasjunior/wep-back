package br.com.wep.app.Services.Entities;

import br.com.wep.app.config.TokenService;
import br.com.wep.app.model.Entities.Comment;
import br.com.wep.app.model.Entities.User;
import br.com.wep.app.model.Repos.CommentRepo;
import br.com.wep.app.model.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TokenService tokenService;

    public List<Comment> index(){
        return (List<Comment>) commentRepo.findAll();
    };

    public Boolean delete(int comment_id, String token){
        try{
            User user = userRepo.getUserByEmail(tokenService.decodeToken(token).getSubject());
            Comment comment = commentRepo.findById(comment_id).get();

            if (user == null || comment == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            if (user.getId() != comment.getUser().getId()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

            commentRepo.deleteById(comment.getId());

            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}

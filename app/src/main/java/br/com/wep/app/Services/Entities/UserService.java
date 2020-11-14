package br.com.wep.app.Services.Entities;

import br.com.wep.app.config.TokenService;
import br.com.wep.app.model.Entities.User;
import br.com.wep.app.model.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import br.com.wep.app.config.md5Password;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TokenService tokenService;

    public List<User> index(){return (List<User>) userRepo.findAll();}

    public Object store(User user){
        try{
            String password = user.getPassword();
            user.setPassword(md5Password.md5(password));
            userRepo.save(user);
            List<Object> retornos = new ArrayList<>();
            retornos.add(tokenService.generateToken(user));
            retornos.add(user.getId());
            return retornos;
        }catch (Exception e){
            return e;
        }
    }

    public List<User> getFollowing(int user_id, String auth) throws Exception{
        User decode = userRepo.getUserByEmail(tokenService.decodeToken(auth).getSubject());
        User user = userRepo.findById(user_id).get();

        if(decode.getId() != user.getId()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        };

        return user.getFollowing();
    }

    public List<User> getFollowers(int user_id, String auth) throws Exception{
        User decode = userRepo.getUserByEmail(tokenService.decodeToken(auth).getSubject());
        User user = userRepo.findById(user_id).get();

        if(decode.getId() != user.getId()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        };

        return user.getFollowers();
    }

    public User doFollow(int new_follow_id, String auth){
        User user = userRepo.getUserByEmail(tokenService.decodeToken(auth).getSubject());
        User new_follow_user = userRepo.findById(new_follow_id).get();

        user.setFollowing(new_follow_user);
        new_follow_user.setFollowers(user);

        userRepo.save(new_follow_user);
        return userRepo.save(user);
    }

    public Object doUnfollow(int unfollow_id, String auth) {
        try{
            User user = userRepo.getUserByEmail(tokenService.decodeToken(auth).getSubject());
            User unfollow_user = userRepo.findById(unfollow_id).get();

            user.removeFollowing(unfollow_user);
            unfollow_user.removeFollowers(user);

            userRepo.save(unfollow_user);
            return userRepo.save(user);
        }catch (Exception e){
            return e;
        }
    }
}

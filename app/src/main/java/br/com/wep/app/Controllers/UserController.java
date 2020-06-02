package br.com.wep.app.Controllers;

import br.com.wep.app.model.Entities.User;
import br.com.wep.app.model.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepo repo;

    @GetMapping
    public List<User> getUsers(){
        List<User> users = (List<User>) repo.findAll();
        return users;
    }

    //Registro de usuarios...necessita instanciar um novo usuario
    //FALTA EFETUAR AS VALIDAÇÔES <----------------------
    @PostMapping
    public User registerUser(User user){
        return repo.save(user);
    }

    //Adicionar amigo...necessita dos ids do usuario e do amigo
    @PutMapping(path = "/friends")
    public boolean makeFriend(@RequestParam(name = "user") User user,
                              @RequestParam(name = "friend") User friend) {
        try {
            Optional<User> u = repo.findById(user.getId());

            if(u != null) {
                user.setFriend(friend);
                repo.save(user);
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return false;
    }
}

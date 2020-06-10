package br.com.wep.app.Controllers;

import br.com.wep.app.model.Entities.User;
import br.com.wep.app.model.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

//@RestController declara essa classe como um controller
@RestController
//@RequestMapping a rota "mãe" de todas as rotas
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepo repo;

    @GetMapping
    public List<User> getUsers(){
        List<User> users = (List<User>) repo.findAll();
        return users;
    }

    //Registro de usuarios...usuario "instanciado" como parametro
    //FALTA EFETUAR AS VALIDAÇÔES <----------------------
    @PostMapping
    public User registerUser(User user){
        return repo.save(user);
    }

    //Adicionar amigo...necessita dos ids do usuario e do amigo
    @PutMapping(path = "/friends")
    public boolean makeFriend(@RequestParam(name = "userID") int userID,
                              @RequestParam(name = "friendID") int friendID) {
        try {
            Optional<User> user = repo.findById(userID);
            User userFound = user.get();

            Optional<User> friend = repo.findById(friendID);
            User friendFound = friend.get();

            userFound.setFriend(friendFound);
            repo.save(userFound);

            return true;
        }catch (Exception e){
            return false;
        }
    }


    //Retorna todos os amigos de um usuario...espera o ID de algum usuario
    @GetMapping(path = "/friends/{userID}")
    public List<User> getFriendsByUser(@PathVariable(name = "userID") int userID){
        try {
            Optional<User> user = repo.findById(userID);
            User userFound = user.get();

            List<User> friends = userFound.getFriends();
            return friends;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

}

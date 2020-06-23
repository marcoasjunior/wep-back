package br.com.wep.app.Controllers;

import br.com.wep.app.model.Entities.Event;
import br.com.wep.app.model.Entities.User;
import br.com.wep.app.model.Repos.UserRepo;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        try{
            return repo.save(user);
        }catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @GetMapping(path = "/{userID}")
    public User getUserById(@PathVariable(name = "userID") int userID){
        User user = repo.findById(userID).get();

        return user;
    }
}

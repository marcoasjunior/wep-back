package br.com.wep.app.Controllers;

import br.com.wep.app.model.Entities.User;
import br.com.wep.app.model.Repos.UserRepo;
import br.com.wep.app.util.md5Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController declara essa classe como um controller
@CrossOrigin
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

    //Recebe um JSON com email e password
    //Se o usuario não existir retorna uma Exception
    //Caso somente a senha esteja incorreta retorna null
    //Se email e senha estejam corretos retorna o id do usuario
    @PostMapping("/auth")
    public Integer auth(@RequestBody User user) throws Exception {
        User foundUser = repo.getUserByEmail(user.getEmail());

        if(foundUser == null){
            throw new Exception("O usuario não existe");
        };

        if(foundUser.getPassword().equals(md5Password.md5(user.getPassword()))){
            return foundUser.getId();
        };

        return null;
    }

    @PostMapping
    public User registerUser(@RequestBody User user){
        try{
            String password = user.getPassword();
            user.setPassword(md5Password.md5(password));
            return repo.save(user);
        }catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @GetMapping(path = "/{userID}")
    public User getUserById(@PathVariable(name = "userID") int userID){
        try{
            User user = repo.findById(userID).get();
            return user;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}

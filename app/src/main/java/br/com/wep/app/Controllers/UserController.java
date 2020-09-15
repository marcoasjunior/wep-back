package br.com.wep.app.Controllers;

import br.com.wep.app.config.TokenService;
import br.com.wep.app.model.Entities.User;
import br.com.wep.app.model.Repos.UserRepo;
import br.com.wep.app.config.md5Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.security.auth.login.CredentialException;
import java.util.List;

//@RestController declara essa classe como um controller
@CrossOrigin
@RestController
//@RequestMapping a rota "mãe" de todas as rotas
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserRepo repo;

    private TokenService tokenService;

    @GetMapping
    public List<User> getUsers(){
        List<User> users = (List<User>) repo.findAll();
        return users;
    }

    //Recebe um JSON com email e password
    @CrossOrigin
    @PostMapping("/auth")
    public Object auth(@RequestBody User user) throws Exception {

        User foundUser = repo.getUserByEmail(user.getEmail());

        if(foundUser == null){
            throw new CredentialException("Algo errado com as informações inseridas!");
        };

        if(!foundUser.getPassword().equals(md5Password.md5(user.getPassword()))){
            throw new CredentialException("Algo errado com as informações inseridas!");
        };

        return tokenService.generateToken(foundUser);
    }

    @CrossOrigin
    @PostMapping("/authToken")
    public Object authToken(@RequestBody String Authentication) throws Exception {
        try {
            return tokenService.decodeToken(Authentication).getSubject();
        } catch (Exception e) {
            return e;
        }
    }

    @PostMapping
    public User registerUser(@RequestBody User user){
        try{
            String password = user.getPassword();
            user.setPassword(md5Password.md5(password));
            return repo.save(user);

        }catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    @GetMapping(path = "/{userID}")
    public User getUserById(@PathVariable(name = "userID") int userID,
                            @RequestHeader(name = "Authentication") String Authentication) throws Exception{

        try{
            User token = repo.getUserByEmail(tokenService.decodeToken(Authentication).getSubject());
            User user = repo.findById(userID).get();

            if (token == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
            ;

            if (!(token.getEmail() == user.getEmail()) && !(token.getId() == user.getId())) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
            ;

            return user;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @CrossOrigin
    @GetMapping(path = "/mail/{user_email}")
    public User getUserByEmail(@PathVariable String user_email){
        try{
            User user = repo.getUserByEmail(user_email);
            return user;
        }catch (Exception e){
            System.out.println("erro: " + e);
        }
        return null;
    }

    @DeleteMapping(path = "/{userID}")
    public boolean deleteUser(@PathVariable Integer userID, @RequestHeader String Authentication) throws Exception{

        String token = tokenService.decodeToken(Authentication).getSubject();
        User user = repo.getUserByEmail(token);

        if(user == null){
            throw new CredentialException("Acesso negado");
        };

        if(!userID.equals(user.getId())){
            throw new CredentialException("Acesso negado");
        };

        repo.deleteById(userID);
        return true;
    }

    //alterar usuario
    @PutMapping
    public Object updateUser(@RequestBody User newUser, @RequestHeader String Authentication){
        try{
            String token = tokenService.decodeToken(Authentication).getSubject();
            User user = repo.getUserByEmail(token);

            if(newUser.getAvatar() != ""){
                user.setAvatar(newUser.getAvatar());
                System.out.println("A");
            }
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            if(newUser.getPassword() != ""){
                user.setPassword(md5Password.md5(newUser.getPassword()));
            }
            user.setWhatsapp(newUser.getWhatsapp());

            repo.save(user);

            return tokenService.generateToken(user);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}

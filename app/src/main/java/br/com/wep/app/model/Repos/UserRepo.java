package br.com.wep.app.model.Repos;

import br.com.wep.app.model.Entities.User;
import org.springframework.data.repository.CrudRepository;

//Repositorio faz a mediação entre o db e os controllers
public interface UserRepo extends CrudRepository<User, Integer> {


}

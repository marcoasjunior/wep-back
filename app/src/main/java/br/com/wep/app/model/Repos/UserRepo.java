package br.com.wep.app.model.Repos;

import br.com.wep.app.model.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {


}

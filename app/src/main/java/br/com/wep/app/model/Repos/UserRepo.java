package br.com.wep.app.model.Repos;

import br.com.wep.app.model.Entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.TransactionRequiredException;

//Repositorio faz a mediação entre o db e os controllers
public interface UserRepo extends CrudRepository<User, Integer> {

    public User getUserByEmail(String email);

    @Modifying
    @Query("update User users set users.avatar = ?1 where users.id = ?2")
    public int updateAvatar(String avatar, int id) throws TransactionRequiredException;
}

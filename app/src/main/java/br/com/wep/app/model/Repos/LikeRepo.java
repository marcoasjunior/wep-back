package br.com.wep.app.model.Repos;

import br.com.wep.app.model.Entities.Like;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LikeRepo extends CrudRepository<Like, Integer> {

    @Query(value = "SELECT id FROM LIKE where user = :userId and event = :eventId", nativeQuery = true)
    public Optional<Like> findLikebyEventAndUser(int userId, int eventId);

}
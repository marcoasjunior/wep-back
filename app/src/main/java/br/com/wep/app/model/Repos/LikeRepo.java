package br.com.wep.app.model.Repos;

import br.com.wep.app.model.Entities.Like;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LikeRepo extends CrudRepository<Like, Integer> {

    @Query(value = "SELECT like FROM Like like WHERE like.user = :userId and like.event = :eventId", nativeQuery = true)
    Like findLikebyEventAndUser(@Param("userId")int userId,@Param("eventId") int eventId);

}
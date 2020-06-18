package br.com.wep.app.model.Repos;

import br.com.wep.app.model.Entities.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepo extends CrudRepository<Comment, Integer> {
}

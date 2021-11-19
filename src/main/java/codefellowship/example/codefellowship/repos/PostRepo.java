package codefellowship.example.codefellowship.repos;


import codefellowship.example.codefellowship.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo  extends CrudRepository<Post,Integer> {
}

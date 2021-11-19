package codefellowship.example.codefellowship.repos;

import codefellowship.example.codefellowship.models.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepo extends CrudRepository<ApplicationUser,Integer> {

    ApplicationUser findByUsername(String username);
    ApplicationUser findUserById(int id);
//    ApplicationUser getOne(long followUser);

}

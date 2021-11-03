package com.example.codefellowship.Repo;

import com.example.codefellowship.Model.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<ApplicationUser,Integer> {
    ApplicationUser findByUsername(String username);

}
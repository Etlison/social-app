package com.laserviewtechnologies.socialapp.repository;

import com.laserviewtechnologies.socialapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Find a user by their username
    Optional<User> findByUsername(String username);

    // Find a user by their email (optional, depending on your app's requirements)
    Optional<User> findByEmail(String email);
}


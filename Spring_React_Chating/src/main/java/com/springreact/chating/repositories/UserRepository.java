package com.springreact.chating.repositories;

import com.springreact.chating.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);

}

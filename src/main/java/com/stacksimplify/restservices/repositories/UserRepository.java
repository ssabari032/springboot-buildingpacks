package com.stacksimplify.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stacksimplify.restservices.entity.User;
import org.springframework.stereotype.Repository;


//Repository
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}

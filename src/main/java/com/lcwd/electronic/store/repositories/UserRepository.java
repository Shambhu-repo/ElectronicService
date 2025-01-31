package com.lcwd.electronic.store.repositories;

import com.lcwd.electronic.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // here we can write any metho that will give us implementation :  "findBy + attributeName" eg : findByEmail
    // if we want to add multiple field then :  "findby+ attributename + and + attributename" eg  findByEmailAndPassword
    //etc
 Optional<User> findByEmail(String email);

//Optional<User> finByEmailAndPassword(String email, String password);

List<User> findByNameContaining(String keyword);

}

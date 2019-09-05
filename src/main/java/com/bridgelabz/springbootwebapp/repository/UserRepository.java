package com.bridgelabz.springbootwebapp.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bridgelabz.springbootwebapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

Optional<User> findByEmailID(String emailID);

Optional<User> findByMobNum(Long mobNum);

}

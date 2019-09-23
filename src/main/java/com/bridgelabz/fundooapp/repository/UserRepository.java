package com.bridgelabz.fundooapp.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundooapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

Optional<User> findByEmailID(String emailID);

Optional<User> findByMobNum(Long mobNum);

}

package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickName(String nickName);
    Optional<User> findByPassword(String password);
    User findByNickNameAndPassword(String nickName, String password);
}

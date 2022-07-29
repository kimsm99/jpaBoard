package com.example.demo.repo;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickName(String nickName);
    Optional<User> findByPassword(String password);
    User findByNickNameAndPassword(String nickName, String password);
}

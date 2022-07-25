package com.example.demo;

import com.example.demo.post.Board;
import com.example.demo.user.User;
import com.example.demo.user.UserController;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserController userController;

    @Test
    @Order(1)
    @DisplayName("회원가입")
    void checkUser() {
        //give
        User user = new User("이름","ab12","cd34");

        //when
        userService.checkUser("이름","ab12","cd34","cd34");
        User savedUser = userRepository.findByNickNameAndPassword(user.getNickName(), user.getPassword());

        //then
        assertEquals(savedUser.getUserName(),user.getUserName());
        assertEquals(savedUser.getNickName(),user.getNickName());
        assertEquals(savedUser.getPassword(),user.getPassword());

    }

    @Test
    @Order(2)
    @DisplayName("로그인")
    void loginError() {
        //give

        String nickName = "ab12";
        String password = "cd34";
        //when
        int result = userService.loginError(nickName,password);

        //then
        assertEquals(result, 0);

    }
}


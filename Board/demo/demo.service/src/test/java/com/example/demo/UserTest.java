
package com.example.demo;


import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.user.UserController;
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


    @Nested
    @DisplayName("오류")
    class wrong{
        @Nested
        @DisplayName("회원가입 오류")
        class signup{
            @Test
            @Order(1)
            @DisplayName("중복된 아이디")
            void duplicateNickName() {
                //give
                User user = new User("이름","ab12","1234");
                String result = userService.checkUser("이름","ab12","1234", "1234");
                assertEquals(result, "중복된 id 입니다.");
            }

            @Test
            @Order(2)
            @DisplayName("중복된 비밀번호")
            void duplicatePassword() {
                User user = new User("이름","abcd","cd34");
                String result = userService.checkUser("이름","abcd","cd34", "cd34");
                assertEquals(result, "중복된 pw 입니다.");
            }

            @Test
            @Order(3)
            @DisplayName("아이디 4자 이하")
            void nickNameLength() {
                User user = new User("이름","abc","1234");
                String result = userService.checkUser("이름","abc","1234", "1234");
                assertEquals(result, "닉네임을 4자 이상 입력하세요");

            }

            @Test
            @Order(4)
            @DisplayName("잘못된 형식의 아이디")
            void wrongFormat() {
                User user = new User("이름","아이디입니다","1234");
                String result = userService.checkUser("이름","아이디입니다","1234", "1234");
                assertEquals(result, "알파벳 대소문자와 숫자로만 입력하세요");

            }

            @Test
            @Order(5)
            @DisplayName("비밀번호 4자 이하")
            void passwordLength() {
                User user = new User("이름","abcd","123");
                String result = userService.checkUser("이름","abcd","123", "123");
                assertEquals(result, "비밀번호를 4자 이상 입력하세요");

            }

            @Test
            @Order(6)
            @DisplayName("아이디가 포함된 비밀번호")
            void wrongPassword() {
                User user = new User("이름","abcd","abcd123");
                String result = userService.checkUser("이름","abcd","abcd123", "abcd123");
                assertEquals(result, "비밀번호에 닉네임을 포함할 수 없습니다.");

            }

            @Test
            @Order(7)
            @DisplayName("비밀번호 불일치")
            void difference() {
                User user = new User("이름","abcd","1234");
                String result = userService.checkUser("이름","abcd","1234", "abcd123");
                assertEquals(result,  "비밀번호 불일치");

            }


        }



        @Nested
        @DisplayName("로그인 오류")
        class login{
            @Test
            @Order(1)
            @DisplayName("아이디 입력 오류")
            void wrongNickName() {

                String nickName = "ab123";
                String password = "cd34";
                int result = userService.loginError(nickName,password);
                assertEquals(result, 1);
            }

            @Test
            @Order(1)
            @DisplayName("비밀번호 입력 오류")
            void wrongPassword() {

                String nickName = "ab12";
                String password = "cd344";
                int result = userService.loginError(nickName,password);
                assertEquals(result, 2);
            }

        }


    }
}




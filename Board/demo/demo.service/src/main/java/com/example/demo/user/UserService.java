package com.example.demo.user;


import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String checkUser(String userName,String nickName,String password,String passwordCheck) {
        String error = "";

        // 회원 ID 중복 확인
        Optional<User> foundId = userRepository.findByNickName(nickName);
        Optional<User> foundPw = userRepository.findByPassword(password);

        String pattern = "^[a-zA-Z0-9]*$";

        if (foundId.isPresent()) {
            return "중복된 id 입니다.";
        }
        if (foundPw.isPresent()) {
            return "중복된 pw 입니다.";
        }

        if (nickName.length() < 4) {
            return "닉네임을 4자 이상 입력하세요";
        } else if (!Pattern.matches(pattern, nickName)) {
            return "알파벳 대소문자와 숫자로만 입력하세요";
        } else if (password.length() < 4) {
            return "비밀번호를 4자 이상 입력하세요";
        } else if (password.contains(nickName)) {
            return "비밀번호에 닉네임을 포함할 수 없습니다.";
        } else if (!password.equals(passwordCheck)) {
            return "비밀번호 불일치";
        }

        User user = new User(userName,nickName,password);
        userRepository.save(user);

        return error;
    }

    public int loginError(String nickName, String password){
        Optional<User> foundNick = userRepository.findByNickName(nickName);
        Optional<User> foundPass = userRepository.findByPassword(password);
        int loginCheck = 0;

        if(!foundNick.isPresent()){
            loginCheck=1;
        } else if (!foundPass.isPresent()) {
            loginCheck=2;
        }

        return loginCheck;
    }

}

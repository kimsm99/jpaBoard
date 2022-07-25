package com.example.demo.user;


import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String checkUser(String username,String nickName,String password,String passwordCheck) {
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

        if (nickName.length() < 3) {
            return "닉네임을 3자 이상 입력하세요";
        } else if (!Pattern.matches(pattern, nickName)) {
            return "알파벳 대소문자와 숫자로만 입력하세요";
        } else if (password.length() < 4) {
            return "비밀번호를 4자 이상 입력하세요";
        } else if (password.contains(nickName)) {
            return "비밀번호에 닉네임을 포함할 수 없습니다.";
        } else if (!password.equals(passwordCheck)) {
            return "비밀번호 불일치";
        }

        User user = new User(username,nickName,password);
        userRepository.save(user);

        return error;
    }

}

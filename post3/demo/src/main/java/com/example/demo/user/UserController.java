package com.example.demo.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    public interface SessionConst {
        String LOGIN_USER = "loginUser";
    }

    //회원가입 페이지 이동
    @RequestMapping("/signupView")
    public String signupView() {
        return "signup";
    }


    //유저
    // 등록
    @RequestMapping("/signup")
    //@ResponseBody//일반 스트링 값 넘겨줌
    public String signup(@RequestParam("username") String username,
                         @RequestParam("nickName") String nickName,
                         @RequestParam("password") String password,
                         @RequestParam("password") String passwordCheck,
                         Model model) {

        if(userService.checkUser(username,nickName,password,passwordCheck).equals("")){
            return "redirect:/boardView";
        }
        else {
            model.addAttribute("errortext", userService.checkUser(username,nickName,password,passwordCheck));
            return "signup";
        }

    }




    @RequestMapping("/login")
    public String loginV3(String nickName,String password, HttpServletResponse response, HttpServletRequest request) {


        System.out.println(nickName+"   "+password);
        User user = userRepository.findByNickNameAndPassword(nickName, password);

        if (user == null) {

            System.out.println("여기들어옴11");

            return "redirect:/boardView";
        }

        //세션 매니저를 통해 세션 생성및 회원정보 보관
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, user);
        System.out.println("여기들어옴22");

        return "redirect:/boardView";
    }

    @RequestMapping("/logout")
    public String logoutV3(HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/boardView";
    }
}

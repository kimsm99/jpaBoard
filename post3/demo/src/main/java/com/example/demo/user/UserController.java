package com.example.demo.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    public String signup(User user,
                         @RequestParam("password") String passwordCheck,
                         Model model) {


        if(userService.checkUser(user.getUserName(), user.getNickName(), user.getPassword(),passwordCheck).equals("")){
            return "redirect:/boardView";
        }
        else {
            model.addAttribute("errortext", userService.checkUser(user.getUserName(), user.getNickName(), user.getPassword(),passwordCheck));
            return "signup";
        }

    }




    @RequestMapping("/login")
    public String loginV3(String nickName, String password, HttpServletResponse response, HttpServletRequest request, RedirectAttributes redirectAttributes) {


        System.out.println(nickName+"   "+password);
        User user = userRepository.findByNickNameAndPassword(nickName, password);


        if (user == null) {

            System.out.println("여기들어옴11");
            int check = userService.loginError(nickName,password);
            redirectAttributes.addAttribute("checkLogin", check);
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

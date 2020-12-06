package com.summit.whms.main;

import com.summit.whms.config.auth.LoginUser;
import com.summit.whms.config.auth.dto.SessionUser;
import com.summit.whms.main.service.MainService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
//@RequestMapping("/main")
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    MainService userService;

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        LOGGER.info("called / ");
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "main/index";
    }

    @GetMapping("/main")
    public String mainView(Model model, @LoginUser SessionUser user) {
        LOGGER.info("mainView is called");
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "main/index" ;
    }

    @GetMapping("/main/signIn")
    public String signInView(Model model) {
        LOGGER.info("signInView is called");
        return "/mains/signIn";
    }

    @GetMapping("/main/signIn2")
    public void signIn2View(Model model) {
        LOGGER.info("signIn2View is called");
//        return "/mains/signIn2";
    }

    @GetMapping("/main/signUp")
    public String signUpView() {
        LOGGER.info("signUpView is called");
        return "mains/signUp";
    }


    @GetMapping("/main/contents")
    public String mainView(Model model, @LoginUser SessionUser user, HttpServletRequest request){
//        LOGGER.info("mainView is called");
//        String userId = "unknown";
//        try{
//            userId = SessionManager.getUserInfo(request).getUserId();
//        } catch (Exception e) {
//            LOGGER.error("user id is unknown");
//        }
//
//        model.addAttribute("id",userId);
//
//        return "contents/main";

        LOGGER.info("contents/main is called");
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "contents/main";
    }

    @GetMapping("/main/mustache")
    public String mustacheView() {
        return "mains/index";
    }

    @GetMapping("/main/signUp2")
    public void signUp2View() {
        LOGGER.info("signUpView is called");
    }
}

package com.summit.whms.main;

import com.summit.whms.config.auth.LoginUser;
import com.summit.whms.config.auth.dto.SessionUser;
import com.summit.whms.core.session.SessionManager;
import com.summit.whms.core.session.SessionModel;
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
@RequestMapping("/main")
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    MainService userService;

    private final HttpSession httpSession;

    @GetMapping
    public String mainView(Model model, @LoginUser SessionUser user) {
        LOGGER.info("mainView is called");
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "/mains/main";
    }

    @GetMapping("/signIn")
    public String signInView(Model model) {
        LOGGER.info("signInView is called");
        return "/mains/signIn";
    }

    @GetMapping("/signIn2")
    public String signIn2View(Model model) {
        LOGGER.info("signIn2View is called");
        return "/mains/signIn2";
    }

    @PostMapping("/signInProcess")
    public void signInProcess(@ModelAttribute UserModel userModel, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        LOGGER.info("Called signInProcess.");
        try {
            if (null == userModel.getInputEmail()) {
                response.sendRedirect("/main/signIn");
            } else if (null == userModel.getInputPassword()) {
                response.sendRedirect("/main/signIn");
            } else {
                SessionModel sessionModel = SessionManager.getUserInfo(request);
                sessionModel.setUserId(userModel.getInputEmail());
                SessionManager.setNewSessionData(request, sessionModel);
                LOGGER.info("=====session 설정 결과 User ID: " + sessionModel.getUserId());

                response.sendRedirect("/contents/main");
            }
        } catch (Exception e) {
            LOGGER.error("SignIn is Failed.");
        }
    }

    @GetMapping("/signUp")
    public String signUpView() {
        LOGGER.info("signUpView is called");
        return "mains/signUp";
    }

    @PostMapping("/signUpProcess")
    public String signUpProcess(@ModelAttribute UserModel userModel, HttpServletRequest request,
                              HttpServletResponse response, ModelMap modelMap) {
        LOGGER.info("Called signUpProcess");
        try{
            userService.insertUserInfo(userModel);
            return "mains/success";
        } catch (Exception e) {
            LOGGER.error("SignUp is Failed.");
            return "mains/fail";
        }
    }

    @GetMapping("/contents")
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

    @GetMapping("/signout")
    public String signout(Model model, HttpServletRequest request, HttpServletResponse response) {
        String userId = "unknown";
        try {
            userId = SessionManager.getUserInfo(request).getUserId();
            if(userId.equals("")){
                response.sendRedirect("/main/signIn2");
            }
        } catch (Exception e) {
            LOGGER.error("user id is unknonw.");
        }

        SessionManager.invalidate(request);

        model.addAttribute("id", userId);
        return "/mains/signout";
    }

    @GetMapping("/mustache")
    public String mustacheView() {
        return "mains/index";
    }

    @GetMapping("/signUp2")
    public String signUp2View() {
        return "mains/signUp2";
    }
}

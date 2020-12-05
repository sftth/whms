package com.summit.whms.main;

import com.summit.whms.core.session.SessionManager;
import com.summit.whms.core.session.SessionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignInController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignInController.class);

    @GetMapping("/signIn")
    public String signInView(Model model) {
        LOGGER.info("signInView is called");
        return "mains/signIn";
    }

    @GetMapping("/mains/main")
    public String mainView(Model model) {
        LOGGER.info("mainView is called");
        return "mains/main";
    }
    @PostMapping("/signInProcess")
    public void signInProcess(@ModelAttribute UserModel userModel, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        LOGGER.info("Called signInProcess.");
        LOGGER.info("InputEmail: " + userModel.getInputEmail());
        LOGGER.info("InputPassword: " + userModel.getInputPassword());
        try {
            if (null == userModel.getInputEmail()) {
                response.sendRedirect("/signIn");
            } else if (null == userModel.getInputPassword()) {
                response.sendRedirect("/signIn");
            } else {
                if (null == SessionManager.getUserInfo(request)) {
                    SessionModel sessionModel = new SessionModel();
                    sessionModel.setUserId(userModel.getInputEmail());
                    SessionManager.setNewSessionData(request, sessionModel);
                    LOGGER.info("=====session 설정 결과 User ID: " + sessionModel.getUserId());
                }
                response.sendRedirect("mains/main");
            }
        } catch (Exception e) {
            LOGGER.error("SignIn is Failed.");
        }
    }
}

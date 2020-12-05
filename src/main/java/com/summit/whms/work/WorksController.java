package com.summit.whms.work;

import com.summit.whms.core.session.SessionManager;
import com.summit.whms.core.session.SessionModel;
import com.summit.whms.main.UserModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WorksController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorksController.class);

    @GetMapping("/works/workInfo")
    public String workInfoView(HttpServletRequest request, Model model) throws Exception {
        SessionModel sessionModel = SessionManager.getUserInfo(request);
        if(sessionModel.getUserId() != null) {
            return "works/workInfo";
        } else {
            return "mains/signIn";
        }
    }
    @GetMapping("/works/studyInfo")
    public String studyInfoView(Model model) {
        return "works/studyInfo";
    }
    @GetMapping("/works/projectInfo")
    public String projectInfoView(Model model) {
        return "works/projectInfo";
    }
}

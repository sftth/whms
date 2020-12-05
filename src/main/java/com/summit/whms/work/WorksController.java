package com.summit.whms.work;

import com.summit.whms.config.auth.LoginUser;
import com.summit.whms.config.auth.dto.SessionUser;
import com.summit.whms.core.session.SessionManager;
import com.summit.whms.core.session.SessionModel;

import com.summit.whms.work.dto.WorksResponseDto;
import com.summit.whms.work.service.WorksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WorksController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorksController.class);

    @Autowired
    private WorksService worksService;

    @GetMapping("/works/workInfo")
    public String workInfoView(HttpServletRequest request, Model model) throws Exception {
        SessionModel sessionModel = SessionManager.getUserInfo(request);
        if(sessionModel.getUserId() != null) {
            return "/works/workInfo";
        } else {
            return "/main/signIn2";
        }
    }

    @GetMapping("/contents/works")
    public String worksView(Model model,@LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        model.addAttribute("posts", worksService.findAllDesc());
        return "/contents/works";
    }

    @GetMapping("/contents/works/save")
    public String worksRegistration() {
        return "/contents/works-save";
    }

    @GetMapping("/contents/works/update/{id}")
    public String worksUpdate(@PathVariable Long id, Model model) {
        WorksResponseDto dto = worksService.findById(id);
        model.addAttribute("post", dto);

        return "/contents/works-update";
    }

}

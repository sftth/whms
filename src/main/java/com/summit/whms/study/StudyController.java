package com.summit.whms.study;

import com.summit.whms.main.service.MainService;
import com.summit.whms.main.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class StudyController {

    @Autowired
    MainService mainService;

    @GetMapping("/studyInfo")
    public String getStudyInfo() {
        String info = mainService.getStudyInfo();
        return info;
    }

    @GetMapping("/userInfo")
    public ModelAndView getUserInfo() {
        ModelAndView mv = new ModelAndView();
        UserModel userModel = mainService.getUserInfo("jacob@me.com");

        mv.addObject("list", userModel);

        return mv;
    }
}

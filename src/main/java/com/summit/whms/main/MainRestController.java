package com.summit.whms.main;

import com.summit.whms.core.session.SessionManager;
import com.summit.whms.core.session.SessionModel;
import com.summit.whms.main.dto.MainResponseDto;
import com.summit.whms.main.dto.UserSignUpRequestDto;
import com.summit.whms.main.service.MainService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class MainRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainRestController.class);

    private final MainService mainService ;

    @GetMapping("/main/dtotest")
    public MainResponseDto getDto(@RequestParam("name") String name,
                                  @RequestParam("email") String email,
                                  @RequestParam("password") String password) {
        return new MainResponseDto(name, email, password);
    }

    @PostMapping("/main/api/v1/signup")
    public String signUp(@RequestBody UserSignUpRequestDto requestDto) throws IOException {
        try {
            String returnVal = mainService.save(requestDto);
            return returnVal;
        } catch (Exception e) {
            LOGGER.error("SignUp is Failed");
        }
        return null;
    }

    @PostMapping("/main/api/v1/signin")
    public void signin(@RequestBody UserSignUpRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            LOGGER.info("Called signInProcess.");
            if (null == requestDto.getEmail()) {
                response.sendRedirect("/main/signIn2");
            } else if (null == requestDto.getPasswd()) {
                response.sendRedirect("/main/signIn2");
            } else {
                SessionModel sessionModel = SessionManager.getUserInfo(request);
                sessionModel.setUserId(requestDto.getEmail());
                SessionManager.setNewSessionData(request, sessionModel);
                LOGGER.info("=====session 설정 결과 User ID: " + sessionModel.getUserId());
            }
        } catch (Exception e) {
            LOGGER.error("signIn is Failed.");
        }
    }

}

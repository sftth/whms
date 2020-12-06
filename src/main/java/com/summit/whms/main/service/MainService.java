package com.summit.whms.main.service;

import com.summit.whms.main.UserModel;
import com.summit.whms.main.dto.UserSignUpRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MainService {
    public String save(UserSignUpRequestDto requestDto);
}

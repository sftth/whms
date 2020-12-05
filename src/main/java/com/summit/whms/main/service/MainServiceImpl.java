package com.summit.whms.main.service;

import com.summit.whms.main.UserModel;
import com.summit.whms.main.domain.users.UsersRepository;
import com.summit.whms.main.dto.UserSignUpRequestDto;
import com.summit.whms.mapper.WhmsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MainServiceImpl implements MainService {
    private final UsersRepository usersRepository;

    @Autowired
    public WhmsMapper whmsMapper;

    @Override
    public String getStudyInfo() {
        return "detail info";
    }

    @Override
    public UserModel getUserInfo(String email) {
        return whmsMapper.selectUserInfo(email);
    }

    @Override
    public void insertUserInfo(UserModel userModel) {
        whmsMapper.insertUserInfo(userModel);
    }

    @Transactional
    @Override
    public String save(UserSignUpRequestDto requestDto) {
        return usersRepository.save(requestDto.toEntity()).getEmail();
    }


}

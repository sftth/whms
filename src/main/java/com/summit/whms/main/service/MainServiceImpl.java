package com.summit.whms.main.service;

import com.summit.whms.main.UserModel;
import com.summit.whms.main.domain.users.UsersRepository;
import com.summit.whms.main.dto.UserSignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MainServiceImpl implements MainService {
    private final UsersRepository usersRepository;

    @Transactional
    @Override
    public String save(UserSignUpRequestDto requestDto) {
        return usersRepository.save(requestDto.toEntity()).getEmail();
    }


}

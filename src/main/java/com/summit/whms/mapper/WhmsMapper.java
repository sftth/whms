package com.summit.whms.mapper;

import com.summit.whms.main.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WhmsMapper {
    public UserModel selectUserInfo(String email);
    public void insertUserInfo(UserModel userModel);
}

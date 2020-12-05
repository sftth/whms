package com.summit.whms.study;

import com.summit.whms.main.service.MainService;
import com.summit.whms.main.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudyMapperTest {
    @Autowired
    private MainService mainService;

//    @Test
//    public void selectUserInfoTest() throws Exception {
//        UserModel userModel = this.mainService.getUserInfo("jacob@me.com");
//        assertThat(userModel.getInputPassword()).isEqualTo("pass01");
//    }

    @Test
    public void insertUserInfoTest() throws Exception {
        UserModel userModel = new UserModel("tester","tester1@me.com","testpass");
        this.mainService.insertUserInfo(userModel);

        UserModel getUserModel = mainService.getUserInfo(userModel.getInputEmail());
        assertThat(getUserModel.getInputPassword()).isEqualTo("testpass");
    }
}

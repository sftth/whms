package com.summit.whms.study;

import com.summit.whms.main.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
//@JdbcTest
//@ActiveProfiles("test")
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudyJdbcTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void selectUserInfoTest() throws Exception {
//        jdbcTemplate.update("INSERT INTO users (name,email,passwd) VALUES(?,?,?)","jacob10","jacob10@me.com","pass10");
//        String password = jdbcTemplate.queryForObject("SELECT passwd FROM users WHERE email= ?", new Object[]{"jacob2@me.com"}, String.class);
////        UserModel userModel = this.studyService.getUserInfo("jacob@me.com");
//        System.out.println("Password" + password);
//        assertThat(password.equals("pass01"));
    }
}

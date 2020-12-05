package com.summit.whms.main;

import com.summit.whms.main.domain.users.Role;
import com.summit.whms.main.domain.users.User;
import com.summit.whms.main.domain.users.UsersRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

    @After
    public void cleanup() {
        usersRepository.deleteAll();
    }

    @Test
    public void getUsersTest() {
        //given
        String name = "jacob10";
        String email = "jacob10@me.com";
        String passwd = "pass011";
        String picture = "picture";

        usersRepository.save(User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .passwd(passwd)
                .role(Role.USER)
                .build());
        //when
//        List<Users> usersList = usersRepository.findAll();

        Optional<User> user = usersRepository.findByEmail(email);
        //then
//        Users users = usersList.get(0);

        assertThat(user.get().getEmail()).isEqualTo(email);
        assertThat(user.get().getName()).isEqualTo(name);
        assertThat(user.get().getPasswd()).isEqualTo(passwd);

    }
}

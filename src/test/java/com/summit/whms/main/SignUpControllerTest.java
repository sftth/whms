package com.summit.whms.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.summit.whms.main.domain.users.Role;
import com.summit.whms.main.domain.users.User;
import com.summit.whms.main.domain.users.UsersRepository;
import com.summit.whms.main.dto.UserSignUpRequestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignUpControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsersRepository usersRepository;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        usersRepository.deleteAll();
    }

//    @Test
//    public void mainViewTest() throws Exception{
//        //when
//        String page = this.testRestTemplate.getForObject("/main", String.class);
//
//        //then
//        assertThat(page).contains("Whms");
//    }

    @Test
    public void signUpViewTest() {
        //when
        String body = this.testRestTemplate.getForObject("/main/signUp2", String.class);

        //then
        assertThat(body).contains("Sign Up");
    }

    @Test
    @WithMockUser(roles = "USER")
    public void signUpTest() throws Exception {
        //given
        String url = "http://localhost:" + port + "/main/api/v1/signup";

        String name = "jacob";
        String email = "jacob@me.com";
        String passwd = "pass01";

        UserSignUpRequestDto requestDto = UserSignUpRequestDto.builder()
                .name(name)
                .email(email)
                .passwd(passwd)
                .role(Role.USER)
                .build();
        //when
//        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(
//                url, requestDto, String.class);

        mockMvc.perform(post(url)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isEqualTo(email);
        Optional<User> users = usersRepository.findByEmail(email);
        assertThat(users.get().getName()).isEqualTo(name);
        assertThat(users.get().getEmail()).isEqualTo(email);
        assertThat(users.get().getPasswd()).isEqualTo(passwd);

    }

    @Test
    public void mustacheViewTest() {
        //when
        String body = this.testRestTemplate.getForObject("/main/mustache", String.class);
        //then
        assertThat(body).contains("Test");
    }
}

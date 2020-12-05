package com.summit.whms.inflearn.sample;

import com.summit.whms.main.UserModel;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.image.SampleModel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@JsonTest
public class LessonServiceTest {
    @Autowired
    private JacksonTester<UserModel> json;

    @Test
    public void webClientTest() throws Exception {
        UserModel userModel = new UserModel("jacob","test@me.com", "1234");

        assertThat(this.json.write(userModel)).extractingJsonPathStringValue("inputPassword")
                .isEqualTo("1234");
    }

    @Test
    public void testDeserialize() throws Exception {
        String content = "{\"inputName\":\"test\",\"inputEmail\":\"test@me.com\",\"inputPassword\":\"passwd\"}";
        assertThat(this.json.parse(content))
                .isEqualTo(new UserModel("test","test@me.com","passwd"));
        assertThat(this.json.parseObject(content).getInputEmail()).isEqualTo("test@me.com");
    }
}
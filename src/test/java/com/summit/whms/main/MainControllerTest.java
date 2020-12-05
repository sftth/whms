package com.summit.whms.main;

import com.summit.whms.core.session.SessionModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {
//    @Autowired
//    private WebClient webClient;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void signInViewTest() throws Exception {
        String url = "/main/signIn2";

        mockMvc.perform(get(url))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/mains/signIn2"))
                .andExpect(content().string(containsString("Sign In")));

    }



}

package com.summit.whms.main;

import com.summit.whms.main.dto.UserSignUpRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = MainRestController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getDtoTest() throws Exception {
        String name = "jacob";
        String email = "jacob@me.com";
        String password = "pass01";

        mockMvc.perform( get("/main/dtotest").param("name",name).param("email", email).param("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.email",is(email)))
                .andExpect(jsonPath("$.password",is(password)));

    }
}

package com.summit.whms.work;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = WorksController.class)
@AutoConfigureMockMvc
public class WorksControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(roles = "USER")
    public void worksViewTest() throws Exception {
        String url = "/contents/works";

        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("contents/works"))
                .andExpect(content().string(containsString("Work Lists")));

    }
}

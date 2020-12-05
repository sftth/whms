package com.summit.whms.inflearn.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
public class SampleController3Test {

    @Autowired
    WebTestClient webTestClient; //dependency에 webflux 추가 필요, Async로 동작함(요청에 대해 기다리지 않고 콜백으로 결과 리턴함)

    @MockBean
    SampleServiceImpl mockSampleService;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void hello() throws Exception {
        when(mockSampleService.getName()).thenReturn("Summit");

//        webTestClient.get().uri("/sample/hello").exchange()
//                .expectStatus().isOk()
//                .expectBody(String.class).isEqualTo("hello Summit");

        mockMvc.perform(get("/sample/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello Summit"))
                .andDo(print());
    }
}

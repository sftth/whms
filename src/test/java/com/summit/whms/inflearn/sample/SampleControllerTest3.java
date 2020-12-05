package com.summit.whms.inflearn.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SampleControllerTest3 {

    @Autowired
    WebTestClient webTestClient; //dependency에 webflux 추가 필요, Async로 동작함(요청에 대해 기다리지 않고 콜백으로 결과 리턴함)

    @MockBean
    SampleServiceImpl mockSampleService;

    @Test
    public void hello() throws Exception {
        when(mockSampleService.getName()).thenReturn("Summit");

        webTestClient.get().uri("/sample/hello").exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello Summit");
    }
}

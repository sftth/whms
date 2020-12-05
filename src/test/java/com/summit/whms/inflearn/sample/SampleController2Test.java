package com.summit.whms.inflearn.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
@AutoConfigureMockMvc
public class SampleController2Test {
    /**
     * TestRestTemplate 활용 예제
     */
    @Autowired
    TestRestTemplate testRestTemplate;
    /**
     * hello2 테스트
     * sampleservice bean을 mock으로 대체
     */
    @MockBean
    SampleService mockSampleService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void helloTest() throws Exception {
        when(mockSampleService.getName()).thenReturn("Tester"); //서비스단까지 가지않고 테스트를 컨트롤러까지만 하고 싶을 때 mockbean으로 교체함

        String result = testRestTemplate.getForObject("/sample/hello", String.class); //restTemplate에 url과 원하는 결과의 body type을 지정함
        assertThat(result).isEqualTo("hello Tester");
    }

    @Test
    public void mockMvcHelloTest() throws Exception {
        when(mockSampleService.getName()).thenReturn("Tester"); //서비스단까지 가지않고 테스트를 컨트롤러까지만 하고 싶을 때 mockbean으로 교체함
        String url = "/sample/hello";

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string("hello Tester"))
                .andDo(print());
    }

}

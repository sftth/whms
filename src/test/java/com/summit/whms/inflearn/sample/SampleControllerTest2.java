package com.summit.whms.inflearn.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SampleControllerTest2 {
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
    SampleServiceImpl mockSampleService;

//    @Test
//    public void hello1() throws  Exception {
//        String result = testRestTemplate.getForObject("/sample/hello", String.class);
//        assertThat(result).isEqualTo("hello Jacob");
//    }

    @Test
    public void hello2() throws Exception {
        when(mockSampleService.getName()).thenReturn("Tester"); //서비스단까지 가지않고 테스트를 컨트롤러까지만 하고 싶을 때 mockbean으로 교체함

        String result = testRestTemplate.getForObject("/sample/hello", String.class); //restTemplate에 url과 원하는 결과의 body type을 지정함
        assertThat(result).isEqualTo("hello Tester");
    }

}

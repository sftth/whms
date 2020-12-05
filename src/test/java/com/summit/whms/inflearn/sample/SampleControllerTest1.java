package com.summit.whms.inflearn.sample;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class SampleControllerTest1 {
    /**
     * MockMvc 활용 예제
     * @AutoConfigureMockMvc 추가 필요
     *
     */
    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        mockMvc.perform(get("/sample/hello"))
                .andExpect(status().isOk()) //서비스 상태가 200(정상상태)이고
                .andExpect(content().string("hello Jacob")) //요청에 대한 결과 컨텐츠
                .andDo(print()); //요청 출력
        // 어떤 컨트롤러의 어떤 메소드를 호출했는지도 확인 가능
    }

}

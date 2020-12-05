package com.summit.whms.inflearn.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
//@WebMvcTest(SampleController.class) //slice test용으로 딱 원하는 컨트롤러만 테스트
public class SampleController4Test {
    @MockBean
    SampleServiceImpl mockSampleService;

    @Autowired
    MockMvc mockMvc;

//    @Test
    public void hello() throws Exception {
        when(mockSampleService.getName()).thenReturn("Jacob");

        mockMvc.perform(get("/sample/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello Jacob"))
                .andDo(print());
    }

}

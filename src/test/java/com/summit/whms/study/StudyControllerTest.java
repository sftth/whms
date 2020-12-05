package com.summit.whms.study;

import com.summit.whms.main.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
////@WebMvcTest(StudyController.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class StudyControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    MainService mockMainService;

//    @Test
    public void getStudyInfoTest() throws Exception {
        given(this.mockMainService.getStudyInfo())
                .willReturn("study info");
        this.mvc.perform(MockMvcRequestBuilders.get("/studyInfo").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("study info"));
    }
}

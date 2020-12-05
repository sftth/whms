package com.summit.whms.work;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.HttpHeader;
import com.summit.whms.core.session.SessionModel;
import com.summit.whms.work.domain.posts.Works;
import com.summit.whms.work.domain.posts.WorksRepository;
import com.summit.whms.work.dto.WorksSaveRequestDto;
import com.summit.whms.work.dto.WorksUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WorksRestControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private WorksRepository worksRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        worksRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void WorksSaveTest() throws Exception {

        //given
        String title = "title";
        String content = "content";

        WorksSaveRequestDto requestDto = WorksSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();
        String url = "http://localhost:" + port + "/api/v1/works";

        //when
//        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, requestDto, Long.class);
        mockMvc.perform(post(url)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Works> all = worksRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void worksUpdateTest() throws Exception{
        //given
        Works savedWorks = worksRepository.save(Works.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedWorks.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        WorksUpdateRequestDto requestDto =
                WorksUpdateRequestDto.builder()
                        .title(expectedTitle)
                        .content(expectedContent)
                        .build();

        String url = "http://localhost:" + port +
                "/api/v1/works/" + updateId;

        HttpEntity<WorksUpdateRequestDto> requestEntity =
                new HttpEntity<>(requestDto);

        //when
//        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(
//                url, HttpMethod.PUT, requestEntity, Long.class
//        );

        mockMvc.perform(put(url)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
        List<Works> all = worksRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }
}

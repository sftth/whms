package com.summit.whms.work;

import com.summit.whms.work.domain.posts.Works;
import com.summit.whms.work.domain.posts.WorksRepository;
import com.summit.whms.work.dto.WorkListResponseDto;
import com.summit.whms.work.dto.WorksUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorksRepositoryTest {

    @Autowired
    WorksRepository worksRepository;

//    @After
    @Before
    public void cleanup() {
        worksRepository.deleteAll();
    }

    @Test
    public void worksSaveTest(){
        //given
        String title = "테스트 게시글";
        String content ="테스트 본문";

        worksRepository.save(Works.builder()
        .title(title)
        .content(content)
        .author("tester@me.com")
        .build());

        //when
        List<Works> worksList = worksRepository.findAll();

        //then
        Works works = worksList.get(0);

        assertThat(works.getTitle()).isEqualTo(title);
        assertThat(works.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntityTest() {
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        worksRepository.save(Works.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

        //when
        List<Works> worksList = worksRepository.findAll();

        //then
        Works works = worksList.get(0);

        System.out.println(">>> createDate=" + works.getCreatedDate() +
                ", modifiedDate="+works.getModifiedDate());

        assertThat(works.getCreatedDate()).isAfter(now);
        assertThat(works.getModifiedDate()).isAfter(now);
    }
}

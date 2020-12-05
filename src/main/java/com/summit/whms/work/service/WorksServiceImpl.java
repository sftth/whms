package com.summit.whms.work.service;

import com.summit.whms.work.domain.posts.Works;
import com.summit.whms.work.dto.WorkListResponseDto;
import com.summit.whms.work.domain.posts.WorksRepository;
import com.summit.whms.work.dto.WorksResponseDto;
import com.summit.whms.work.dto.WorksSaveRequestDto;
import com.summit.whms.work.dto.WorksUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorksServiceImpl implements WorksService{
    private final WorksRepository worksRepository;

    @Override
    @Transactional
    public Long save(WorksSaveRequestDto requestDto) {
        return worksRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    @Transactional
    public Long update(Long id, WorksUpdateRequestDto requestDto) {
        Works works = worksRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("There is not content. id="+ id)
        );
        works.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Override
    @Transactional
    public WorksResponseDto findById(Long id) {
        Works entity = worksRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "There is not content. id=" + id
                ));
        return new WorksResponseDto(entity);
    }


    @Transactional(readOnly = true)
    @Override
    public List<WorkListResponseDto> findAllDesc() {

        return worksRepository.findAllDesc().stream()
                .map(WorkListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Works entity = worksRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "There is not content. id=" +id
                ));
        worksRepository.delete(entity);
    }
}

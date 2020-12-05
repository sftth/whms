package com.summit.whms.work.service;

import com.summit.whms.work.dto.WorkListResponseDto;
import com.summit.whms.work.dto.WorksResponseDto;
import com.summit.whms.work.dto.WorksSaveRequestDto;
import com.summit.whms.work.dto.WorksUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface WorksService {
    public Long save(WorksSaveRequestDto requestDto);

    public Long update(Long id, WorksUpdateRequestDto requestDto);

    public WorksResponseDto findById(Long id);

    public List<WorkListResponseDto> findAllDesc() ;

    public void delete(Long id);
}

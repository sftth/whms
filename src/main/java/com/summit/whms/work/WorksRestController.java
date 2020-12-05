package com.summit.whms.work;

import com.summit.whms.work.dto.WorksResponseDto;
import com.summit.whms.work.dto.WorksSaveRequestDto;
import com.summit.whms.work.dto.WorksUpdateRequestDto;
import com.summit.whms.work.service.WorksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class WorksRestController {
    private final WorksService worksService;

    @PostMapping("/api/v1/works")
    public Long save(@RequestBody WorksSaveRequestDto requestDto) {
        return worksService.save(requestDto);
    }

    @PutMapping("/api/v1/works/{id}")
    public Long update(@PathVariable Long id, @RequestBody WorksUpdateRequestDto requestDto) {
        return worksService.update(id, requestDto);
    }

    @GetMapping("/api/v1/works/{id}")
    public WorksResponseDto findById(@PathVariable Long id) {
        return worksService.findById(id);
    }

    @DeleteMapping("/api/v1/works/{id}")
    public Long delete(@PathVariable Long id) {
        worksService.delete(id);

        return id;
    }

}

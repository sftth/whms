package com.summit.whms.work.domain.posts;

import com.summit.whms.work.dto.WorksUpdateRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorksRepository extends JpaRepository<Works, Long> {
    @Query("SELECT p FROM Works p order by p.id desc")
    List<Works> findAllDesc();
}

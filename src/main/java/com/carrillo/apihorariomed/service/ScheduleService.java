package com.carrillo.apihorariomed.service;

import com.carrillo.apihorariomed.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {

    Page<Schedule> findAll(Pageable pageable);

    Page<Schedule> search(String texto, Pageable pageable);

    Schedule findById(Integer id);

    Schedule create(Schedule schedule);

    Schedule update(Integer id, Schedule schedule);

    void deleteById(Integer id);
}


package com.carrillo.apihorariomed.repository;


import com.carrillo.apihorariomed.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    // Buscar horarios por nombre del medicamento o vitamina
    Page<Schedule> findScheduleByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}

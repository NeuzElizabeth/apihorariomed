package com.carrillo.apihorariomed.service.impl;

import com.carrillo.apihorariomed.entity.Schedule;
import com.carrillo.apihorariomed.repository.ScheduleRepository;
import com.carrillo.apihorariomed.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository repository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Schedule> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Schedule> search(String texto, Pageable pageable) {
        return repository.findScheduleByNombreContainingIgnoreCase(texto, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Schedule findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Horario no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public Schedule create(Schedule schedule) {
        return repository.save(schedule);
    }

    @Override
    @Transactional
    public Schedule update(Integer id, Schedule schedule) {

        Schedule scheduleExistente = findById(id);

        scheduleExistente.setNombre(schedule.getNombre());
        scheduleExistente.setDosis(schedule.getDosis());
        scheduleExistente.setHora(schedule.getHora());
        scheduleExistente.setFrecuencia(schedule.getFrecuencia());
        scheduleExistente.setNotas(schedule.getNotas());
        scheduleExistente.setActivo(schedule.getActivo());

        return repository.save(scheduleExistente);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Schedule schedule = findById(id);
        repository.deleteById(schedule.getId());
    }
}


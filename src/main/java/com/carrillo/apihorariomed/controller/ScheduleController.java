package com.carrillo.apihorariomed.controller;

import com.carrillo.apihorariomed.entity.Schedule;
import com.carrillo.apihorariomed.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
@CrossOrigin(origins = "*")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // GET: Obtener todos los horarios con paginación
    @GetMapping
    public ResponseEntity<Page<Schedule>> getAllSchedules(
            @RequestParam(defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").ascending());
        Page<Schedule> schedules = scheduleService.findAll(pageable);

        return ResponseEntity.ok(schedules);
    }

    // GET: Buscar horarios por nombre del medicamento
    @GetMapping("/search")
    public ResponseEntity<Page<Schedule>> searchSchedules(
            @RequestParam String texto,
            @RequestParam(defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").ascending());
        Page<Schedule> schedules = scheduleService.search(texto, pageable);

        return ResponseEntity.ok(schedules);
    }

    // GET: Obtener horario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Integer id) {
        Schedule schedule = scheduleService.findById(id);
        return ResponseEntity.ok(schedule);
    }

    // POST: Crear nuevo horario
    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        Schedule nuevoSchedule = scheduleService.create(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSchedule);
    }

    // PUT: Actualizar horario existente
    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(
            @PathVariable Integer id,
            @RequestBody Schedule schedule) {

        Schedule scheduleActualizado = scheduleService.update(id, schedule);
        return ResponseEntity.ok(scheduleActualizado);
    }

    // DELETE: Eliminar horario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Integer id) {
        scheduleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

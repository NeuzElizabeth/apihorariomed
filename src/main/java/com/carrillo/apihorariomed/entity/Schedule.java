package com.carrillo.apihorariomed.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Nombre del medicamento o vitamina
    @Column(nullable = false)
    private String nombre;

    // Dosis (ej: 1 tableta)
    @Column(nullable = false)
    private String dosis;

    // Hora de consumo (HH:mm)
    @Column(nullable = false, length = 5)
    private String hora;

    // Frecuencia (Diario, Lun-Mie-Vie, etc.)
    @Column(nullable = false)
    private String frecuencia;

    // Observaciones opcionales
    private String notas;

    // Estado activo/inactivo
    @Column(nullable = false)
    private Boolean activo;
}

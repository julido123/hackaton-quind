package com.uco.hackathon.entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "enfrentamiento")
public class Enfrentamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipo_local_id", referencedColumnName = "id")
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name = "equipo_visitante_id", referencedColumnName = "id")
    private Equipo equipoVisitante;

    @Column(name = "fecha_enfrentamiento")
    private LocalDate fechaEnfrentamiento;

    @ManyToOne
    @JoinColumn(name = "fixture_id")
    private Fixture fixture;

    public Enfrentamiento(Equipo equipoLocal, Equipo equipoVisitante, LocalDate fechaEnfrentamiento) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.fechaEnfrentamiento = fechaEnfrentamiento;
    }

    public Enfrentamiento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public LocalDate getFechaEnfrentamiento() {
        return fechaEnfrentamiento;
    }

    public void setFechaEnfrentamiento(LocalDate fechaEnfrentamiento) {
        this.fechaEnfrentamiento = fechaEnfrentamiento;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }

}

package com.uco.hackathon.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "equipo")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "numero_jugadores")
    private Integer numeroJugadores;

    @ManyToOne
    @JoinColumn(name = "torneo_id", referencedColumnName = "id")
    private Torneo torneo;

    public Equipo() {
    }

    public Equipo(String nombre, String descripcion, Integer numeroJugadores, Torneo torneo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.numeroJugadores = numeroJugadores;
        this.torneo = torneo;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter para numeroJugadores
    public Integer getNumeroJugadores() {
        return numeroJugadores;
    }

    // Setter para numeroJugadores
    public void setNumeroJugadores(Integer numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

}
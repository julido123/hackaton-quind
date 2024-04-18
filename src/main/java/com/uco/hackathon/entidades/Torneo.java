package com.uco.hackathon.entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "torneo")
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "deporte")
    private String deporte;

    @Column(name = "descripcion")
    private String descripcion;


    public Torneo(String nombre, String ubicacion, String deporte, String descripcion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.deporte = deporte;
        this.descripcion = descripcion;
    }

    public Torneo() {
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Long  getId() {
        return id;
    }
}
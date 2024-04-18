package com.uco.hackathon.entidades;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "fixture")
public class Fixture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Enfrentamiento> getEnfrentamientos() {
        return enfrentamientos;
    }

    public void setEnfrentamientos(List<Enfrentamiento> enfrentamientos) {
        this.enfrentamientos = enfrentamientos;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    @OneToMany(mappedBy = "fixture", cascade = CascadeType.ALL)
    private List<Enfrentamiento> enfrentamientos;

    @OneToOne
    @JoinColumn(name = "torneo_id")
    private Torneo torneo;

}

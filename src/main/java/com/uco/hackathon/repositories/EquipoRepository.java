package com.uco.hackathon.repositories;
import com.uco.hackathon.entidades.Equipo;
import com.uco.hackathon.entidades.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    boolean existsByNombre(String nombre);
    Equipo findByNombre(String nombre);
    List<Equipo> findAllByTorneo(Torneo torneo);

}

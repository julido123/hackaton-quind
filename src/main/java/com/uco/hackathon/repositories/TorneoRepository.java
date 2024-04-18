package com.uco.hackathon.repositories;
import com.uco.hackathon.entidades.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TorneoRepository extends JpaRepository<Torneo, Long> {
    boolean existsByNombre(String nombre);
    Torneo findByNombre(String nombre);
}

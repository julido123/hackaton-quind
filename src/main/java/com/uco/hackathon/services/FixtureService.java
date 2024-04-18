package com.uco.hackathon.services;


import com.uco.hackathon.entidades.Enfrentamiento;
import com.uco.hackathon.entidades.Equipo;
import com.uco.hackathon.entidades.Fixture;
import com.uco.hackathon.entidades.Torneo;
import com.uco.hackathon.repositories.EnfrentamientoRepository;
import com.uco.hackathon.repositories.EquipoRepository;
import com.uco.hackathon.repositories.FixtureRepository;
import com.uco.hackathon.repositories.TorneoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@Service
public class FixtureService {
    private final FixtureRepository fixtureRepository;
    private final EnfrentamientoRepository enfrentamientoRepository;
    private final EquipoRepository equipoRepository;
    private final TorneoRepository torneoRepository;

    public FixtureService(FixtureRepository fixtureRepository, EnfrentamientoRepository enfrentamientoRepository, EquipoRepository equipoRepository, TorneoRepository torneoRepository) {
        this.fixtureRepository = fixtureRepository;
        this.enfrentamientoRepository = enfrentamientoRepository;
        this.equipoRepository = equipoRepository;
        this.torneoRepository = torneoRepository;
    }

    @Transactional
    public Fixture crearYGuardarFixture(Torneo torneo) {
        List<Equipo> equipos = equipoRepository.findAllByTorneo(torneo);
        Fixture fixture = new Fixture();
        fixture.setTorneo(torneo);
        List<Enfrentamiento> enfrentamientos = generarEnfrentamientos(equipos, fixture);
        fixture.setEnfrentamientos(enfrentamientos);
        fixtureRepository.save(fixture);
        return fixture;
    }

    @Transactional
    public List<Fixture> findAll() {
        return fixtureRepository.findAll();
    }

    @Transactional
    public boolean delete(Long id) {
        if (fixtureRepository.existsById(id)) {
            fixtureRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Genera una lista de enfrentamientos en formato de ida y vuelta para una lista de equipos.
     * Cada equipo juega una vez como local y una vez como visitante contra cada uno de los otros equipos.
     * Los enfrentamientos generados se guardan en la base de datos.
     */
    private List<Enfrentamiento> generarEnfrentamientos(List<Equipo> equipos, Fixture fixture) {
        List<Enfrentamiento> enfrentamientos = new ArrayList<>();
        for (int i = 0; i < equipos.size(); i++) {
            for (int j = 0; j < equipos.size(); j++) {
                if (i != j) {
                    Enfrentamiento enfrentamiento = crearEnfrentamiento(equipos.get(i), equipos.get(j));
                    enfrentamiento.setFixture(fixture);
                    enfrentamientos.add(enfrentamiento);
                    enfrentamientoRepository.save(enfrentamiento);
                }
            }
        }
        return enfrentamientos;
    }

    private Enfrentamiento crearEnfrentamiento(Equipo local, Equipo visitante) {
        Enfrentamiento enfrentamiento = new Enfrentamiento();
        enfrentamiento.setEquipoLocal(local);
        enfrentamiento.setEquipoVisitante(visitante);
        enfrentamiento.setFechaEnfrentamiento(LocalDate.now());
        return enfrentamiento;
    }
}

package com.uco.hackathon.services;
import com.uco.hackathon.entidades.Torneo;
import org.springframework.transaction.annotation.Transactional;
import com.uco.hackathon.repositories.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TorneoService {
    private final TorneoRepository torneoRepository;

    @Autowired
    public TorneoService(TorneoRepository torneoRepository) {
        this.torneoRepository = torneoRepository;
    }

    // Método para obtener todos los torneos
    @Transactional(readOnly = true)
    public List<Torneo> findAll() {
        return torneoRepository.findAll();
    }

    // Método para obtener un torneo por ID
    @Transactional(readOnly = true)
    public Torneo findById(Long id) {
        Optional<Torneo> torneo = torneoRepository.findById(id);
        return torneo.orElse(null);
    }

    @Transactional(readOnly = true)
    public Torneo findByNombre(String nombre) {
        return torneoRepository.findByNombre(nombre);
    }

    // Método para guardar un torneo
    @Transactional
    public Torneo save(Torneo torneo) {
        return torneoRepository.save(torneo);
    }

    // Método para actualizar un torneo
    @Transactional
    public Torneo update(Long id, Torneo torneo) {
        if (torneoRepository.existsById(id)) {
            return torneoRepository.save(torneo);
        }
        return null;
    }

    // Método para eliminar un torneo
    @Transactional
    public boolean delete(Long id) {
        if (torneoRepository.existsById(id)) {
            torneoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public boolean existsByNombre(String nombre) {
        return torneoRepository.existsByNombre(nombre);
    }
}

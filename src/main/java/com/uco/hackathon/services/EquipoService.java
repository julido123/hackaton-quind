package com.uco.hackathon.services;
import com.uco.hackathon.entidades.Equipo;
import com.uco.hackathon.repositories.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {
    private final EquipoRepository equipoRepository;

    @Autowired
    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    // Método para obtener todos los equipos
    @Transactional(readOnly = true)
    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    // Método para obtener un equipo por ID
    @Transactional(readOnly = true)
    public Equipo findById(Long id) {
        Optional<Equipo> equipo = equipoRepository.findById(id);
        return equipo.orElse(null);
    }

    // Método para guardar un equipo
    @Transactional
    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    // Método para actualizar un equipo
    @Transactional
    public Equipo update(Long id, Equipo equipo) {
        if (equipoRepository.existsById(id)) {
            return equipoRepository.save(equipo);
        }
        return null;
    }

    // Método para eliminar un equipo
    @Transactional
    public boolean delete(Long id) {
        if (equipoRepository.existsById(id)) {
            equipoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public boolean existsByNombre(String nombre) {
        return equipoRepository.existsByNombre(nombre);
    }
}

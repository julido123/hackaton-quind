package com.uco.hackathon.controladores;

import com.uco.hackathon.entidades.Torneo;
import com.uco.hackathon.services.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/torneos")
public class TorneoController {

    private final TorneoService torneoService;

    @Autowired
    public TorneoController(TorneoService torneoService) {
        this.torneoService = torneoService;
    }

    @GetMapping
    public ResponseEntity<List<Torneo>> getAllTorneos() {
        List<Torneo> torneos = torneoService.findAll();
        return ResponseEntity.ok(torneos);
    }

    // GET
    @GetMapping("/{id}")
    public ResponseEntity<Torneo> getTorneoById(@PathVariable Long id) {
        Torneo torneo = torneoService.findById(id);
        if (torneo != null) {
            return ResponseEntity.ok(torneo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST - Crear un nuevo torneo
    @PostMapping
    public ResponseEntity<Torneo> createTorneo(@RequestBody Torneo torneo) {
        if (torneo.getNombre() == null || torneo.getNombre().isEmpty() ||
                torneo.getUbicacion() == null || torneo.getUbicacion().isEmpty() ||
                torneo.getDeporte() == null || torneo.getDeporte().isEmpty() ||
                torneo.getDescripcion() == null || torneo.getDescripcion().isEmpty()) {
            // Retorna una respuesta de error si alguno de los campos obligatorios está vacío
            return ResponseEntity.badRequest().body(null);
        }

        // Comprobar si ya existe un torneo con el mismo nombre
        if (torneoService.existsByNombre(torneo.getNombre())) {
            // Retorna una respuesta de conflicto si ya existe un torneo con ese nombre
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        Torneo savedTorneo = torneoService.save(torneo);
        // Retorna una respuesta de éxito con el torneo creado
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTorneo);
    }

    // PUT - Actualizar un torneo existente
    @PutMapping("/{id}")
    public ResponseEntity<Torneo> updateTorneo(@PathVariable Long id, @RequestBody Torneo torneo) {
        Torneo updatedTorneo = torneoService.update(id, torneo);
        if (updatedTorneo != null) {
            return ResponseEntity.ok(updatedTorneo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Eliminar un torneo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTorneo(@PathVariable Long id) {
        boolean deleted = torneoService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

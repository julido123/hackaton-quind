package com.uco.hackathon.controladores;

import com.uco.hackathon.entidades.Equipo;
import com.uco.hackathon.entidades.Torneo;
import com.uco.hackathon.services.EquipoService;
import com.uco.hackathon.services.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {
    private final EquipoService equipoService;
    private final TorneoService torneoService;

    @Autowired
    public EquipoController(EquipoService equipoService, TorneoService torneoService) {
        this.equipoService = equipoService;
        this.torneoService = torneoService;
    }


    @GetMapping
    public ResponseEntity<List<Equipo>> getAllEquipos() {
        List<Equipo> equipos = equipoService.findAll();
        return ResponseEntity.ok(equipos);
    }

    // GET
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getEquipoById(@PathVariable Long id) {
        Equipo equipo = equipoService.findById(id);
        if (equipo != null) {
            return ResponseEntity.ok(equipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST - Crear un nuevo equipo
    @PostMapping
    public ResponseEntity<Equipo> createEquipo(@RequestParam("nombre") String nombre,
                                               @RequestParam("descripcion") String descripcion,
                                               @RequestParam("numeroJugadores") Integer numeroJugadores,
                                               @RequestParam("idTorneo") Long idTorneo) {

        // Verificar si algún parámetro obligatorio es nulo o vacío
        if (nombre == null || nombre.isEmpty() || descripcion == null || descripcion.isEmpty() ||
                numeroJugadores == null || idTorneo == null) {
            // Retorna una respuesta de error si algún parámetro es nulo o vacío
            return ResponseEntity.badRequest().build();
        }

        // Verificar si el torneo asociado al equipo existe
        Torneo torneo = torneoService.findById(idTorneo);
        if (torneo == null) {
            // Retorna una respuesta de error si el torneo no existe
            return ResponseEntity.notFound().build();
        }

        // Verificar si ya existe un equipo con el mismo nombre
        if (equipoService.existsByNombre(nombre)) {
            // Retorna una respuesta de conflicto si ya existe un equipo con ese nombre
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        // Crear el objeto Equipo
        Equipo equipo = new Equipo();
        equipo.setNombre(nombre);
        equipo.setDescripcion(descripcion);
        equipo.setNumeroJugadores(numeroJugadores);
        equipo.setTorneo(torneo); // Asignar el torneo utilizando el objeto Torneo recuperado

        // Guardar el equipo en la base de datos
        Equipo savedEquipo = equipoService.save(equipo);

        // Retorna una respuesta de éxito con el equipo creado
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEquipo);
    }


    // PUT - Actualizar un equipo existente
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
        Equipo updatedEquipo = equipoService.update(id, equipo);
        if (updatedEquipo != null) {
            return ResponseEntity.ok(updatedEquipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Eliminar un equipo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable Long id) {
        boolean deleted = equipoService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

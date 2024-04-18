package com.uco.hackathon.controladores;

import com.uco.hackathon.entidades.Fixture;
import com.uco.hackathon.entidades.Torneo;
import com.uco.hackathon.services.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fixtures")
public class FixtureController {

    private final FixtureService fixtureService;

    @Autowired
    public FixtureController(FixtureService fixtureService) {
        this.fixtureService = fixtureService;
    }

    @GetMapping
    public ResponseEntity<List<Fixture>> getAllTorneos() {
        List<Fixture> fixtures = fixtureService.findAll();
        return ResponseEntity.ok(fixtures);
    }

    @PostMapping
    public ResponseEntity<Fixture> createFixture(@RequestBody Torneo torneo) {
        try {
            Fixture fixture = fixtureService.crearYGuardarFixture(torneo);
            return ResponseEntity.ok(fixture);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFixture(@PathVariable Long id) {
        boolean deleted = fixtureService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


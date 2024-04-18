package com.uco.hackathon;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class Controlador {

    private Repositorio repositorio;

    public Controlador(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    //TODO en este metodo entran las peticiones por servicio REST y se puede ejecturar el código
    @GetMapping(path = "/prueba")
    public Prueba save() {
        Prueba prueba = new Prueba("pedro");
        repositorio.save(prueba);
        return prueba;
    }

}

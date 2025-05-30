package com.fullstack.Edutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.Edutech.model.Contenido;
import com.fullstack.Edutech.service.ContenidoService;

@RestController
@RequestMapping("/contenidos")
public class ContenidoController {
    @Autowired
    private ContenidoService contenidoService;

    //Metodo para crear contenido
    @PostMapping
    public String crearContenido(@RequestBody Contenido contenido){
        return contenidoService.guardarContenido(contenido);
    }

    //Metodo para listar contenido
    @GetMapping
    public List<Contenido> listarContenidos(){
        return contenidoService.listar();
    }
    
    //Metodo para obtener contenido por id
    @GetMapping("/{id}")
    public Contenido obtenerContenidoPorId(@PathVariable int id){
        return contenidoService.obtenerContenidoPorId(id).orElse(null);
    }

    //Meotodo para eliminar contenido
    @DeleteMapping("/{id}")
    public String eliminarContenido(@PathVariable int id){
        return contenidoService.eliminarContenido(id);
    }

    //Metodo para activar contenido
    @PutMapping("/activar/{id}")
    public String activarContenido(@PathVariable int id){
        return contenidoService.activar(id);
    }

    //Metodo para desactivar contenido
    @PutMapping("/desctivar/{id}")
    public String desctivarContenido(@PathVariable int id){
        return contenidoService.desactivar(id);
    }

    
    
}

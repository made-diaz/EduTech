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
import com.fullstack.Edutech.model.Curso;
import com.fullstack.Edutech.service.CursoService;



@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;


    //Metodo para almacennar curso
    @PostMapping
    public String almacenar(@RequestBody Curso curso){
        return cursoService.crearCurso(curso);
    }

    //Metodo para Listar cursos
    @GetMapping
    public List<Curso> listar(){
        return cursoService.listar();
    }

    //Metodo para obtener cursos por Id
    @GetMapping("/{id}")
    public Curso obtenerCursoPorId(@PathVariable int id){
        return cursoService.obtenerCursosPorId(id);
    }

        //Metodo para activar cursos
    @PutMapping("/activar/{id}")
    public String activarCurso(@PathVariable int id){
        return cursoService.activar(id);
    }

    //Metodo para desactivar cursos
    @PutMapping("/desactivar/{id}")
    public String desactivarCurso(@PathVariable int id){
        return cursoService.desactivar(id);
    }


    //Metodo para elimiar curso por Id
    @DeleteMapping("/{id}")
    public String eliminarCurso(@PathVariable int id){
        return cursoService.eliminarCurso(id);
    }

    //Metodo para agregar contenido a curso
    @PostMapping("/{curso_id}/contenido")
    public String agregarContenidoACurso(@PathVariable int  curso_id, @RequestBody Contenido contenido){
        return cursoService.agregarContenido(curso_id, contenido);
    }

}

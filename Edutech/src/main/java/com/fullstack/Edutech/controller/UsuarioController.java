package com.fullstack.Edutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fullstack.Edutech.model.Usuario;
import com.fullstack.Edutech.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService; 


    @PostMapping
    public String almacenar(@RequestBody Usuario usuario){
        return usuarioService.almacenar(usuario); 
    }

    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.listar();
    }

    
    @GetMapping("/{nombre}")
    public List<Usuario> buscar(@PathVariable String nombre){
        return usuarioService.buscar(nombre);
    }

    @PostMapping("/asignar/{id}/{rol_id}")
    public String asignarRol(@PathVariable int id, @PathVariable int rol_id){
        return usuarioService.asignarRol(id, rol_id);
    }
}

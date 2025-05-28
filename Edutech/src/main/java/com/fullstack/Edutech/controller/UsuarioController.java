package com.fullstack.Edutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.Edutech.dto.UsuarioRolDTO;
import com.fullstack.Edutech.model.Usuario;
import com.fullstack.Edutech.service.UsuarioService;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService; 

    //Metodo para almacenar usuarios
    @PostMapping
    public String almacenar(@RequestBody Usuario usuario){
        return usuarioService.almacenar(usuario);
    }


    //Listar a todoss los usuarios
    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.listar();
    }

    
    //Buscar usuarios por palabras incompletas (estu, admi, user)
    @GetMapping("/{nombre}")
    public List<Usuario> buscar(@PathVariable String nombre){
        return usuarioService.buscar(nombre);
    }

    //Asignar a un usuario ya creado {id} un rol {rol_id}
    @PostMapping("/asignar/{id}/{rol_id}")
    public String asignarRol(@PathVariable int id, @PathVariable int rol_id){
        return usuarioService.asignarRol(id, rol_id);
    }

    //Asigna un rol a un usuario pero protegiendo sus datos
    @PostMapping("/asignar")
    public String asignarRol(@RequestBody UsuarioRolDTO dto){
        return usuarioService.asignarRol(dto);
    }

    //Metodo para desactivar usuario
    @PutMapping("/desactivar/{id}")
    public String desactivar(@PathVariable int id){
        return usuarioService.desactivar(id);
    }

}

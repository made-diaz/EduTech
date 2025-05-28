package com.fullstack.Edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.Edutech.dto.UsuarioRolDTO;
import com.fullstack.Edutech.model.Rol;
import com.fullstack.Edutech.model.Usuario;
import com.fullstack.Edutech.repository.RolRepository;
import com.fullstack.Edutech.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;

     public String almacenar(Usuario usuario){
         if(usuarioRepository.findByNombre(usuario.getNombre()) == null){
            usuarioRepository.save(usuario);
            return "Usuario " + usuario.getNombre() + " almacenado correctamente";
         }else{
            return "Usuario " + usuario.getNombre() + " ya se encuentra creado";
         }
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> buscar(String nombre){

        return usuarioRepository.findByNombreContaining(nombre);
    }


    /*public String asignarRol(Rol rol, int id){
        Usuario usuario = usuarioRepository.findById(id);
        usuario.setRol(rol);
    }
    */

    public String asignarRol(int id, int rol_id){
        if(!usuarioRepository.existsById(id)){
            return "El id usuario no existe";
        }else if(!rolRepository.existsById(id)){
            return "El rol ingresado no existe";
        }else {
            Usuario usuario = usuarioRepository.findById(id).get();
            Rol rol = rolRepository.findById(rol_id).get();

            usuario.setRoles(null);
            usuarioRepository.save(usuario);

            return "Rol: " + rol.getNombre() + " asignada correctamente al Usuario " + usuario.getNombre();
        }
    }

    public String asignarRol(UsuarioRolDTO dto){
         if(!usuarioRepository.existsById(dto.getId())){
            return "El id usuario no existe";
        }else if(!rolRepository.existsById(dto.getId_rol())){
            return "El rol ingresado no existe";
        }else {
            Usuario usuario = usuarioRepository.findById(dto.getId()).get();
            Rol rol = rolRepository.findById(dto.getId_rol()).get();

            usuario.setRoles(null);
            usuarioRepository.save(usuario);

            return "Rol: " + rol.getNombre() + " asignada correctamente al Usuario " + usuario.getNombre();
        }
    }

}


package com.fullstack.Edutech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.Edutech.dto.UsuarioCursoDTO;
import com.fullstack.Edutech.dto.UsuarioRolDTO;
import com.fullstack.Edutech.model.Curso;
import com.fullstack.Edutech.model.Rol;
import com.fullstack.Edutech.model.Usuario;
import com.fullstack.Edutech.repository.CursoRepository;
import com.fullstack.Edutech.repository.RolRepository;
import com.fullstack.Edutech.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private CursoRepository cursorRepository;   

    // Método para almacenar un nuevo usuario.
    // Verifica si ya existe un usuario con el mismo nombre para evitar duplicados.
    // Si no existe, lo guarda y devuelve un mensaje de éxito.
    // Si ya existe, devuelve un mensaje indicando que ya está creado.
     public String almacenar(Usuario usuario){
         if(usuarioRepository.findByNombre(usuario.getNombre()) == null){
            usuarioRepository.save(usuario);
            return "Usuario " + usuario.getNombre() + " almacenado correctamente";
         }else{
            return "Usuario " + usuario.getNombre() + " ya se encuentra creado";
         }
    }

    // Método para listar todos los usuarios existentes en la base de datos.
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    // Método para buscar usuarios por una parte de su nombre.
    public List<Usuario> buscar(String nombre){

        return usuarioRepository.findByNombreContaining(nombre);
    }


    // Método para asignar un rol a un usuario existente, utilizando los IDs del usuario y del rol.
    public String asignarRol(int id, int rol_id){
        if(!usuarioRepository.existsById(id)){
            return "El id usuario no existe";
        }else if(!rolRepository.existsById(id)){
            return "El rol ingresado no existe";
        }else{
            Usuario usuario = usuarioRepository.findById(id).get();
            Rol rol = rolRepository.findById(rol_id).get();

            if(usuario.getRoles() == null){
                usuario.setRoles(new ArrayList<>());
            }

            if(!usuario.getRoles().contains(rol)){
                usuario.getRoles().add(rol);
                usuarioRepository.save(usuario);
                return "Rol: " + rol.getNombre() + " asignado correctamente al usuario " + usuario.getNombre();
            }else{
                return "Ya tiene un  rol asignado " + usuario.getNombre() + " ya tiene asignado el rol " + rol.getNombre() + ".";
            }
        }
    }

    // Método sobrecargado para asignar un rol a un usuario, utilizando un objeto DTO
    public String asignarRol(UsuarioRolDTO dto){
         if(!usuarioRepository.existsById(dto.getId())){
            return "El id usuario no existe";
        }else if(!rolRepository.existsById(dto.getId_rol())){
            return "El rol ingresado no existe";
        }else {
            Usuario usuario = usuarioRepository.findById(dto.getId()).get();
            Rol rol = rolRepository.findById(dto.getId_rol()).get();

            if (usuario.getRoles() == null) {
                usuario.setRoles(new ArrayList<>());
            }
            if (!usuario.getRoles().contains(rol)) { // Evitar duplicados
                usuario.getRoles().add(rol);
                usuarioRepository.save(usuario);
                return "Rol: " + rol.getNombre() + " asignado correctamente al Usuario " + usuario.getNombre();
            } else {
                return "El usuario " + usuario.getNombre() + " ya tiene asignado el rol " + rol.getNombre() + ".";
            }
        }
    }

    // Nuevo método para desactivar un usuario
    public String desactivar(int id){
        if(!usuarioRepository.existsById(id)){
            return "No se encontro ningun usuario con con el ID " + id + ".";
        }else{
            Usuario usuario = usuarioRepository.findById(id).get();
            usuario.setActivo(false);
            usuarioRepository.save(usuario);
            return "Usuario con ID " + id + " desactivado Exitosamente";
        }

    }

    //Metodo para activar un usuario
    public String activar(int id){
        if(!usuarioRepository.existsById(id)){
            return "No se encontro ningun usuario con el ID " + id + ".";
        }else{
            Usuario usuario = usuarioRepository.findById(id).get();
            usuario.setActivo(true);
            usuarioRepository.save(usuario);
            return "Usuario con ID " + id + " activado Exitosamente";
        }
    }


    //Metodo para inscribir a un usuario al curso
    public String inscribirUsuarioACurso(UsuarioCursoDTO dto){
        if(!usuarioRepository.existsById(dto.getIdUsuario())){
            return "El Id Usuario no existe";
        }
        if(!cursorRepository.existsById(dto.getIdCurso())){
            return "El Id Curso no existe";
        }

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario()).get();
        Curso curso = cursorRepository.findById(dto.getIdCurso()).get();

        if(usuario.getCursosInscritos().contains(curso)){
            return "El usuario " + usuario.getNombre() + " ya esta inscrito en el curso " + curso.getNombre() + ".";
        }
        
        usuario.getCursosInscritos().add(curso);
        return "Usuario '" + usuario.getNombre() + "' inscrito en el curso '" + curso.getNombre();
    }

    //Metodo eliminar usuario
    public String eliminar(int id){
        if(!usuarioRepository.existsById(id)){
            return "No se encontro ningun usuario con Id " + id + " para Eliminar";
        }else{
            usuarioRepository.deleteById(id);
            return "Usuario con ID " + id  + " eliminado exitosamente";
        }
    }

}


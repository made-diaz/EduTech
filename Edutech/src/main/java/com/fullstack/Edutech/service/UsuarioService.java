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
    // Simplemente recupera y devuelve todos los registros de la tabla de usuarios.
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    // Método para buscar usuarios por una parte de su nombre.
    // Devuelve una lista de usuarios cuyos nombres contengan la cadena 'nombre'
    // que se pasa como argumento. La búsqueda probablemente es insensible a mayúsculas/minúsculas.
    public List<Usuario> buscar(String nombre){

        return usuarioRepository.findByNombreContaining(nombre);
    }


    // Método para asignar un rol a un usuario existente, utilizando los IDs del usuario y del rol.
    // 1. Verifica si el ID de usuario existe. Si no, devuelve un error.
    // 2. Verifica si el ID de rol existe. Si no, devuelve un error.
    // 3. Si ambos existen, recupera el usuario y el rol.
    // 4. Añade el rol a la lista de roles del usuario (asegurándose de que la lista no sea null).
    // 5. Guarda el usuario actualizado en la base de datos.
    // 6. Devuelve un mensaje de éxito.
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

    // Método sobrecargado para asignar un rol a un usuario, utilizando un objeto DTO (Data Transfer Object).
    // Este DTO encapsula los IDs del usuario y del rol para una transferencia de datos más segura o específica.
    // La lógica interna es idéntica al método anterior de 'asignarRol' con IDs individuales.
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

    // Nuevo método para desactivar un usuario
    // 1. Verifica si el ID de usuario existe. Si no, devuelve un error.
    // 2. Si el usuario existe, lo recupera.
    // 3. Establece el campo 'activo' del usuario a 'false'.
    // 4. Guarda el usuario actualizado en la base de datos.
    // 5. Devuelve un mensaje de éxito o de usuario no encontrado.
    public String desactivar(int Id){
        if(!usuarioRepository.existsById(Id)){
            return "No se encontro ningun usuario con con el ID " + Id + ".";
        }else{
            Usuario usuario = usuarioRepository.findById(Id).get();
            usuario.setActivo(false);
            usuarioRepository.save(usuario);
            return "Usuario con ID" + Id + "desactivado Exitosamente";
        }

    }

}


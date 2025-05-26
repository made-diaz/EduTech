package com.fullstack.Edutech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.Edutech.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByNombre(String nombre);

        List<Usuario> findByNombreContaining(String nombre);

}

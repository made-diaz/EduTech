package com.fullstack.Edutech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.Edutech.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer>{
    Rol findBynombre(String nombre);

    List<Rol> findByNombreContaining(String nombre);
}

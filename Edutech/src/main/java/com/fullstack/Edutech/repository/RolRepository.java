package com.fullstack.Edutech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.Edutech.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer>{
    Rol findBynombre(String nombre);
}

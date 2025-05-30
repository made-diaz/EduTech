package com.fullstack.Edutech.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.Edutech.model.Curso;


public interface CursoRepository extends JpaRepository<Curso, Integer> {
    Curso findByNombre(String nombre);
    List<Curso> findByNombreContainingIgnoreCase(String nombre);

}

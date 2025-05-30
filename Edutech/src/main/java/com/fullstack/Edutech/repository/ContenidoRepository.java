package com.fullstack.Edutech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstack.Edutech.model.Contenido;

@Repository
public interface ContenidoRepository extends JpaRepository<Contenido, Integer>{
    Contenido findByTitulo(String titulo);
    List<Contenido> findByTituloContainingIgnoreCase(String titulo);
}

package com.fullstack.Edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.Edutech.model.Curso;
import com.fullstack.Edutech.repository.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;



    public String almacenar(Curso curso){
        if(cursoRepository.findByNombre(curso.getNombre()) == null){
            cursoRepository.save(curso);
            return "Curso: " + curso.getNombre() + " Almacenado correctamente";
        }else{
            return "Curso: " + curso.getNombre() + " ya se encuentra registrado";
        }
    }

    public List<Curso> listar(){

        return cursoRepository.findAll();
    }
}

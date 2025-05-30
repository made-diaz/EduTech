package com.fullstack.Edutech.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fullstack.Edutech.model.Contenido;
import com.fullstack.Edutech.repository.ContenidoRepository;
import com.fullstack.Edutech.repository.CursoRepository;

@Service
public class ContenidoService {

    @Autowired
    private ContenidoRepository contenidoRepository;

/*
    //Metodo para almacenar nuevo contenido
    public String guardarContenido(Contenido contenido) {
    if (contenido.getCurso() == null) {
        throw new IllegalArgumentException("El contenido debe estar asociado a un curso");
    }
    contenidoRepository.save(contenido);
    return "Contenido creado exitosamente";
    }

    //Metodo para listar el contenido ya creado
    public List<Contenido> obtenerTodosLosContenidos(){
        return contenidoRepository.findAll();
    }
 */

    //Metodo poara almacenar contenido
    public String guardarContenido(Contenido contenido){
        if (contenido.getCurso() != null && contenido.getCurso().getId() > 0) { 
        }else{
            contenido.setCurso(null);
        }
        Contenido contenidoGuardado = contenidoRepository.save(contenido);
        return "Contenido '" + contenidoGuardado.getTitulo() + "' guardado exitosamente";
    }



    //Meotodo para listar contenidos
        public List<Contenido> listar() {
        return contenidoRepository.findAll();
    }

    //Metodo Obtiene una lista de contenidos cuyos IDs valor int
    public Optional<Contenido> obtenerContenidoPorId(int id){
        return contenidoRepository.findById(id);
    }

    //metodo para eliminar contenido
    public String eliminarContenido(int id){
        if(contenidoRepository.existsById(id)){
            contenidoRepository.deleteById(id);
            return "Contenido con ID " + id + " eliminado exitosamente";
        }else{
            return "No se encontró contenido con ID " + id + ".";
        }
    }

    //Metodo para activar un contenido
    public String activar(int id){
        if(!contenidoRepository.existsById(id)){
            return "No se encontro ningun contenido con ese Id";
        }else{
            Contenido contenido = contenidoRepository.findById(id).get();
            contenido.setActivo(true);
            contenidoRepository.save(contenido);
            return "Contenido con Id " + id + " activado Exitosamente!";
        }
    }

    //Metodo para desactivar contenido
    public String desactivar(int id){
        if(!contenidoRepository.existsById(id)){
            return "No se encontro ningun contenido con ese Id";
        }else{
            Contenido contenido = contenidoRepository.findById(id).get();
            contenido.setActivo(false);
            contenidoRepository.save(contenido);
            return "Contenido con Id " + id + " desactivado Exitosamente!";        }
    }


}

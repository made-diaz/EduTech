package com.fullstack.Edutech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.Edutech.model.Contenido;
import com.fullstack.Edutech.model.Curso;
import com.fullstack.Edutech.model.Usuario;
import com.fullstack.Edutech.repository.ContenidoRepository;
import com.fullstack.Edutech.repository.CursoRepository;
import com.fullstack.Edutech.repository.UsuarioRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ContenidoRepository contenidoRepository;



    //Metodo para Crear curso..<3
    public String crearCurso(Curso curso){
        if(cursoRepository.findByNombre(curso.getNombre()) == null){
            cursoRepository.save(curso);
            return "curso  '" + curso.getNombre() + "'  creado correctamente.";
        }else{
            return "El curso '" + curso.getNombre() + "' ya existe.";
        }
    }

    //Metodo para listar cursos
    public List<Curso> listar(){

        return cursoRepository.findAll();
    }

    //Metodo para encontrar por ID
    public Curso obtenerCursosPorId(int id){
        return cursoRepository.findById(id).orElse(null);
    }


    //Metodo para eliminar cursos
    public String eliminarCurso(int id){
        if(cursoRepository.existsById(id)){
            cursoRepository.deleteById(id);
            return "Curso con Id " + id + "eliminado Exitosamente!";
        }else{
            return "No se encontro curso con ID " + id + ".";
        }
    }

    //Metodo para inscribir un usuario a un curso
    public String inscribirUsuarioACurso(int usuario_id, int curso_id){
        if(!usuarioRepository.existsById(usuario_id)){
            return "El Id del usuario no existe";
        }
        if(!cursoRepository.existsById(curso_id)){
            return "El Id de curso no existe.";
        }
        Usuario usuario = usuarioRepository.findById(usuario_id).get();
        Curso curso = cursoRepository.findById(curso_id).get();

        if(usuario.getCursosInscritos().contains(curso)){
            return "El usuario '" + usuario.getNombre() + "' ya esta inscrito en el curso '" + curso.getNombre() + "'.";
        }
        
        usuario.getCursosInscritos().add(curso);


        if(curso.getUsuariosInscritos() == null){
            curso.setUsuariosInscritos(new ArrayList<>());
        }
        curso.getUsuariosInscritos().add(usuario);

        usuarioRepository.save(usuario);
        cursoRepository.save(curso);
        return "Usuario '" + usuario.getNombre() + "' esta inscrito en el curso '" + curso.getNombre() + "'.";
    }

    //Metodo todo para agregar contenido en un curso
    public String agregarContenido(int curso_id, Contenido Contenido){
        if(!cursoRepository.existsById(curso_id)){
            return "El Id curso no existe";
        }

        Curso curso = cursoRepository.findById(curso_id).get();

        Contenido.setCurso(curso);
        contenidoRepository.save(Contenido);

        curso.getContenidos().add(Contenido);
        cursoRepository.save(curso);
        return "Contenido '" + Contenido.getTitulo() + "' agregado al curso '" + curso.getNombre() + "' exitosamente.";
    }

    //Metodo para activar un contenido
    public String activar(int id){
        if(!cursoRepository.existsById(id)){
            return "No se encontro ningun contenido con ese Id";
        }else{
            Curso curso = cursoRepository.findById(id).get();
            curso.setActivo(true);
            cursoRepository.save(curso);
            return "Contenido con Id " + id + " activado Exitosamente!";
        }
    }

    //Metodo para desactivar contenido
    public String desactivar(int id){
        if(!cursoRepository.existsById(id)){
            return "No se encontro ningun contenido con ese Id";
        }else{
            Curso curso = cursoRepository.findById(id).get();
            curso.setActivo(false);
            cursoRepository.save(curso);
            return "Contenido con Id " + id + " desactivado Exitosamente!";        }
    }
}


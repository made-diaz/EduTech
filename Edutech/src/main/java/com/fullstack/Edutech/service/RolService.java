package com.fullstack.Edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.Edutech.model.Rol;
import com.fullstack.Edutech.repository.RolRepository;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    public String almacenar (Rol rol){

        Rol validacion = rolRepository.findBynombre(rol.getNombre());
        if(validacion == null){
            rolRepository.save(rol);
            return "Rol almacenado correctamente";
        }else{
            return "Rol " + rol.getNombre() + " ya se encuentra ingresado.";
        }

    }


    public List<Rol> listar(){

        return rolRepository.findAll();
    }

    public List<Rol> buscar(String nombre){

        return rolRepository.findByNombreContaining(nombre);
    }
}


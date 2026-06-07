package com.example.tutoria03.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tutoria03.models.Estudiante;
import com.example.tutoria03.repositories.IEstudianteRepository;

@Service
public class EstudianteService {
    
    // crear una instancia de repository
    @Autowired
    private IEstudianteRepository estudianteRepository;

    public ArrayList<Estudiante> GetAll(){
        return (ArrayList<Estudiante>) estudianteRepository.findAll();
    }

    public Estudiante GetStudentById(int id){
        return estudianteRepository.findById(id).orElse(null);
    }

    public Estudiante save(Estudiante estudiante){
        return !existeEstudiante(estudiante.getId()) 
                ? estudianteRepository.save(estudiante) : null;
    }

    public Estudiante update(Estudiante estudiante){
        return existeEstudiante(estudiante.getId())
             ? estudianteRepository.save(estudiante) : null;
    }

    public boolean Delete(int id){
        if (!existeEstudiante(id)) return false;
        estudianteRepository.deleteById(id);
        return true;
    }

    private boolean existeEstudiante(int id){
        return estudianteRepository.findById(id).orElse(null) != null;
    }

}
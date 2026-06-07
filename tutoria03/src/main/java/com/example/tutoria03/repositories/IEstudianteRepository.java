package com.example.tutoria03.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.tutoria03.models.Estudiante;

@Repository
public interface IEstudianteRepository extends CrudRepository<Estudiante,Integer> {
    // reescribir o hacer consulta sql nativa
}

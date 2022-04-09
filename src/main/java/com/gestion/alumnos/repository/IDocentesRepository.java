package com.gestion.alumnos.repository;

import com.gestion.alumnos.entity.Docentes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocentesRepository extends JpaRepository<Docentes, Integer> {

}
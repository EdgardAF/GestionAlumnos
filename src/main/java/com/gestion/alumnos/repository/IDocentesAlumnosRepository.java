package com.gestion.alumnos.repository;

import com.gestion.alumnos.entity.DocentesAlumnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDocentesAlumnosRepository extends JpaRepository<DocentesAlumnos, Integer> {

    List<DocentesAlumnos> findByIdDocente(Integer idDocente);

    List<DocentesAlumnos> findByIdAlumno(Integer idAlumno);

    Optional<DocentesAlumnos> findByIdDocenteAndIdAlumno(Integer idDocente, Integer idAlumno);

}
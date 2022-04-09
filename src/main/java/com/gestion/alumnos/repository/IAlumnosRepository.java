package com.gestion.alumnos.repository;

import com.gestion.alumnos.entity.Alumnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlumnosRepository extends JpaRepository<Alumnos, Integer> {

}
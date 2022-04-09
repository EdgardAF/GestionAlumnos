package com.gestion.alumnos.controller;

import com.gestion.alumnos.entity.Alumnos;
import com.gestion.alumnos.entity.Docentes;
import com.gestion.alumnos.entity.DocentesAlumnos;
import com.gestion.alumnos.repository.IAlumnosRepository;
import com.gestion.alumnos.repository.IDocentesAlumnosRepository;
import com.gestion.alumnos.repository.IDocentesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/gestionar")
public class AsignarDocentesAlumnosController {

    @Autowired
    private IDocentesRepository docentesRepository;

    @Autowired
    private IAlumnosRepository alumnosRepository;

    @Autowired
    private IDocentesAlumnosRepository docentesAlumnosRepository;

    @PostMapping("/asignar")
    public ResponseEntity<?> asignated(@RequestBody DocentesAlumnos docentesAlumno) {
        Map<String, String> mapMensaje = new HashMap<>();

        Optional<Docentes> docente = docentesRepository.findById(docentesAlumno.getIdDocente());
        if (!docente.isPresent()) {
            mapMensaje.put("Mensaje", "No existe el docente a asignar.");
            return new ResponseEntity<>(mapMensaje, HttpStatus.NOT_FOUND);
        }

        Optional<Alumnos> alumno = alumnosRepository.findById(docentesAlumno.getIdAlumno());
        if (!alumno.isPresent()) {
            mapMensaje.put("Mensaje", "No existe el alumno a asignar.");
            return new ResponseEntity<>(mapMensaje, HttpStatus.NOT_FOUND);
        }

        Optional<DocentesAlumnos> alumnoDocente = docentesAlumnosRepository.findByIdDocenteAndIdAlumno(
                docentesAlumno.getIdDocente(), docentesAlumno.getIdAlumno());

        if (alumnoDocente.isPresent()) {
            mapMensaje.put("Mensaje", "El alumno ya fue asignado al docente.");
            return new ResponseEntity<>(mapMensaje, HttpStatus.FOUND);
        }

        docentesAlumnosRepository.save(docentesAlumno);
        mapMensaje.put("Mensaje", "Alumno asignado correctamente.");
        return new ResponseEntity<>(mapMensaje, HttpStatus.OK);
    }

    @DeleteMapping("/desasignar")
    public ResponseEntity<?> desasignated(@RequestBody DocentesAlumnos docentesAlumno) {
        Map<String, String> mapMensaje = new HashMap<>();

        Optional<DocentesAlumnos> alumno = docentesAlumnosRepository.findByIdDocenteAndIdAlumno(
                docentesAlumno.getIdDocente(), docentesAlumno.getIdAlumno());

        if (alumno.isPresent()) {
            docentesAlumnosRepository.delete(alumno.get());
            mapMensaje.put("Mensaje", "Alumno desasignado correctamente.");
            return new ResponseEntity<>(mapMensaje, HttpStatus.OK);
        }

        mapMensaje.put("Mensaje", "El alumno no tiene asignado el docente.");
        return new ResponseEntity<>(mapMensaje, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listar-alumnos/idDocente/{idDocente}")
    public ResponseEntity<?> getAlumnoByDocente(@PathVariable Integer idDocente) {
        Map<String, String> mapMensaje = new HashMap<>();

        List<DocentesAlumnos> list = docentesAlumnosRepository.findByIdDocente(idDocente);

        if (list.isEmpty() || list.size() == 0) {
            mapMensaje.put("Mensaje", "El docente no cuenta con alumnos asignados.");
            return new ResponseEntity<>(mapMensaje, HttpStatus.NOT_FOUND);
        }

        List<Optional<Alumnos>> listAlumnos = new ArrayList();
        for (DocentesAlumnos docentesAlumnos : list) {
            Optional<Alumnos> alumno = alumnosRepository.findById(docentesAlumnos.getIdAlumno());

            if (alumno.isPresent()) {
                listAlumnos.add(alumno);
            }
        }

        Map<String, List<Optional<Alumnos>>> mapAlumnos = new HashMap<>();
        mapAlumnos.put("Alumnos", listAlumnos);
        return new ResponseEntity<>(mapAlumnos, HttpStatus.OK);
    }

}
package com.gestion.alumnos.controller;

import com.gestion.alumnos.entity.Alumnos;
import com.gestion.alumnos.repository.IAlumnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class AlumnosController {

    @Autowired
    private IAlumnosRepository alumnosRepository;

    @PostMapping("/registrar")
    public ResponseEntity<Alumnos> save(@RequestBody Alumnos alumno) {
        return new ResponseEntity<>(alumnosRepository.save(alumno), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Alumnos>> getAll() {
        List<Alumnos> list = alumnosRepository.findAll();
        if (list.isEmpty() || list.size() == 0) {
            return new ResponseEntity<List<Alumnos>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Alumnos>>(list, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Alumnos> getSingle(@PathVariable Integer id) {
        Optional<Alumnos> alumno = alumnosRepository.findById(id);

        if (alumno.isPresent()) {
            return new ResponseEntity<Alumnos>(alumno.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Alumnos>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Alumnos> update(@PathVariable Integer id, @RequestBody Alumnos alumnos) {
        Optional<Alumnos> alumno = alumnosRepository.findById(id);
        if (alumno.isPresent()) {
            alumno.get().setName(alumnos.getName());
            alumno.get().setAge(alumnos.getAge());
            alumno.get().setEmail(alumnos.getEmail());
            alumno.get().setPhone(alumnos.getPhone());
            return new ResponseEntity<Alumnos>(alumnosRepository.save(alumno.get()), HttpStatus.OK);
        }
        return new ResponseEntity<Alumnos>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        Optional<Alumnos> alumno = alumnosRepository.findById(id);
        if (alumno.isPresent()) {
            alumnosRepository.delete(alumno.get());
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }

}
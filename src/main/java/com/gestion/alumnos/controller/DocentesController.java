package com.gestion.alumnos.controller;

import com.gestion.alumnos.entity.Docentes;
import com.gestion.alumnos.repository.IDocentesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/docentes")
public class DocentesController {

    @Autowired
    private IDocentesRepository docentesRepository;

    @PostMapping("/registrar")
    public ResponseEntity<Docentes> save(@RequestBody Docentes docente) {
        return new ResponseEntity<>(docentesRepository.save(docente), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Docentes>> getAll() {
        List<Docentes> list = docentesRepository.findAll();
        if (list.isEmpty() || list.size() == 0) {
            return new ResponseEntity<List<Docentes>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Docentes>>(list, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Docentes> getSingle(@PathVariable Integer id) {
        Optional<Docentes> docente = docentesRepository.findById(id);

        if (docente.isPresent()) {
            return new ResponseEntity<Docentes>(docente.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Docentes>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Docentes> update(@PathVariable Integer id, @RequestBody Docentes docentes) {
        Optional<Docentes> docente = docentesRepository.findById(id);
        if (docente.isPresent()) {
            docente.get().setName(docentes.getName());
            docente.get().setAge(docentes.getAge());
            docente.get().setEmail(docentes.getEmail());
            docente.get().setPhone(docentes.getPhone());
            return new ResponseEntity<Docentes>(docentesRepository.save(docente.get()), HttpStatus.OK);
        }
        return new ResponseEntity<Docentes>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        Optional<Docentes> docente = docentesRepository.findById(id);
        if (docente.isPresent()) {
            docentesRepository.delete(docente.get());
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }

}
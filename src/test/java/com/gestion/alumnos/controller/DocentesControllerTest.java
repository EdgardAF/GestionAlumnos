package com.gestion.alumnos.controller;

import com.gestion.alumnos.entity.Docentes;
import com.gestion.alumnos.repository.IDocentesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DocentesControllerTest {

    @InjectMocks
    private DocentesController docentesController;

    @Mock
    private IDocentesRepository docentesRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenSave_thenSuccess() {
        Docentes docentes = new Docentes();
        when(docentesRepository.save(docentes)).thenReturn(docentes);
        ResponseEntity<Docentes> response = docentesController.save(docentes);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void whenGetAll_thenSuccess() {
        List<Docentes> listDocentes = new ArrayList<>();
        Docentes docentes = new Docentes();
        docentes.setName("Edgard Alva");
        docentes.setAge(43);
        docentes.setPhone("960191915");
        docentes.setEmail("edgardalvaf@gmail.com");
        listDocentes.add(docentes);

        when(docentesRepository.findAll()).thenReturn(listDocentes);
        ResponseEntity<List<Docentes>> response = docentesController.getAll();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void whenGetAll_thenNoContent() {
        when(docentesRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Docentes>> response = docentesController.getAll();
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void whenGetSingle_thenSuccess() {
        Docentes docentes = new Docentes();
        Integer id = 1;

        when(docentesRepository.findById(id)).thenReturn(java.util.Optional.of(docentes));
        ResponseEntity<Docentes> response = docentesController.getSingle(id);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void whenGetSingle_thenNoFound() {
        Docentes docentes = new Docentes();
        Integer id = 1;

        when(docentesRepository.findById(id)).thenReturn(null);
        ResponseEntity<Docentes> response = docentesController.getSingle(null);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void whenUpdate_thenSuccess() {
        Docentes docentes = new Docentes();
        Integer id = 1;

        when(docentesRepository.findById(id)).thenReturn(java.util.Optional.of(docentes));
        when(docentesRepository.save(docentes)).thenReturn(docentes);
        ResponseEntity<Docentes> response = docentesController.update(id, docentes);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void whenUpdate_thenNoFound() {
        Docentes docentes = new Docentes();
        Integer id = 1;

        when(docentesRepository.findById(id)).thenReturn(null);
        when(docentesRepository.save(docentes)).thenReturn(null);
        ResponseEntity<Docentes> response = docentesController.update(null, docentes);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void whenDelete_thenSuccess() {
        Docentes docentes = new Docentes();
        Integer id = 1;

        when(docentesRepository.findById(id)).thenReturn(java.util.Optional.of(docentes));
        ResponseEntity<HttpStatus> response = docentesController.delete(id);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void whenDelete_thenNoFound() {
        Docentes docentes = new Docentes();
        Integer id = 1;

        when(docentesRepository.findById(id)).thenReturn(null);
        ResponseEntity<HttpStatus> response = docentesController.delete(null);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

}
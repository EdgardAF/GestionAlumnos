package com.gestion.alumnos.controller;

import com.gestion.alumnos.entity.Alumnos;
import com.gestion.alumnos.repository.IAlumnosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AlumnosControllerTest {

    @InjectMocks
    private AlumnosController alumnosController;

    @Mock
    private IAlumnosRepository alumnosRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenSave_thenSuccess() {
        Alumnos alumno = new Alumnos();
        when(alumnosRepository.save(alumno)).thenReturn(alumno);
        ResponseEntity<Alumnos> response = alumnosController.save(alumno);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void whenGetAll_thenSuccess() {
        List<Alumnos> listAlumnos = new ArrayList<>();
        Alumnos alumnos = new Alumnos();
        alumnos.setName("Edgard Alva");
        alumnos.setAge(43);
        alumnos.setPhone("960191915");
        alumnos.setEmail("edgardalvaf@gmail.com");
        listAlumnos.add(alumnos);

        when(alumnosRepository.findAll()).thenReturn(listAlumnos);
        ResponseEntity<List<Alumnos>> response = alumnosController.getAll();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void whenGetAll_thenNoContent() {
        when(alumnosRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Alumnos>> response = alumnosController.getAll();
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void whenGetSingle_thenSuccess() {
        Alumnos alumnos = new Alumnos();
        Integer id = 1;

        when(alumnosRepository.findById(id)).thenReturn(java.util.Optional.of(alumnos));
        ResponseEntity<Alumnos> response = alumnosController.getSingle(id);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void whenGetSingle_thenNoFound() {
        Alumnos alumnos = new Alumnos();
        Integer id = 1;

        when(alumnosRepository.findById(id)).thenReturn(null);
        ResponseEntity<Alumnos> response = alumnosController.getSingle(null);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void whenUpdate_thenSuccess() {
        Alumnos alumnos = new Alumnos();
        Integer id = 1;

        when(alumnosRepository.findById(id)).thenReturn(java.util.Optional.of(alumnos));
        when(alumnosRepository.save(alumnos)).thenReturn(alumnos);
        ResponseEntity<Alumnos> response = alumnosController.update(id, alumnos);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void whenUpdate_thenNoFound() {
        Alumnos alumnos = new Alumnos();
        Integer id = 1;

        when(alumnosRepository.findById(id)).thenReturn(null);
        when(alumnosRepository.save(alumnos)).thenReturn(null);
        ResponseEntity<Alumnos> response = alumnosController.update(null, alumnos);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void whenDelete_thenSuccess() {
        Alumnos alumnos = new Alumnos();
        Integer id = 1;

        when(alumnosRepository.findById(id)).thenReturn(java.util.Optional.of(alumnos));
        ResponseEntity<HttpStatus> response = alumnosController.delete(id);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void whenDelete_thenNoFound() {
        Alumnos alumnos = new Alumnos();
        Integer id = 1;

        when(alumnosRepository.findById(id)).thenReturn(null);
        ResponseEntity<HttpStatus> response = alumnosController.delete(null);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

}
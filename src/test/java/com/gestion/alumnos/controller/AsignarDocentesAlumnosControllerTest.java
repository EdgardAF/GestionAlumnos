package com.gestion.alumnos.controller;

import com.gestion.alumnos.entity.Alumnos;
import com.gestion.alumnos.entity.Docentes;
import com.gestion.alumnos.entity.DocentesAlumnos;
import com.gestion.alumnos.repository.IAlumnosRepository;
import com.gestion.alumnos.repository.IDocentesAlumnosRepository;
import com.gestion.alumnos.repository.IDocentesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AsignarDocentesAlumnosControllerTest {

    @InjectMocks
    private AsignarDocentesAlumnosController asignarDocentesAlumnosController;

    @Mock
    private IDocentesRepository docentesRepository;

    @Mock
    private IAlumnosRepository alumnosRepository;

    @Mock
    private IDocentesAlumnosRepository docentesAlumnosRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenAsignated_thenSuccess() {
        Docentes docentes = new Docentes();
        docentes.setId(1);
        docentes.setName("Alex Cobeñas");
        docentes.setAge(50);

        Alumnos alumnos = new Alumnos();
        alumnos.setId(1);
        alumnos.setName("Edgard Alva");
        alumnos.setAge(43);

        Integer idDocente = 1;
        Integer idAlumno = 1;

        DocentesAlumnos docentesAlumno = new DocentesAlumnos();
        docentesAlumno.setIdDocente(1);
        docentesAlumno.setIdAlumno(1);

        when(docentesRepository.save(docentes)).thenReturn(docentes);
        when(alumnosRepository.save(alumnos)).thenReturn(alumnos);

        when(docentesRepository.findById(idDocente)).thenReturn(Optional.of(docentes));
        when(alumnosRepository.findById(idAlumno)).thenReturn(Optional.of(alumnos));

        ResponseEntity<?> response = asignarDocentesAlumnosController.asignated(docentesAlumno);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void whenAsignated_thenNotFoundDocente() {
        Docentes docentes = new Docentes();
        docentes.setId(1);
        docentes.setName("Alex Cobeñas");
        docentes.setAge(50);

        Alumnos alumnos = new Alumnos();
        alumnos.setId(1);
        alumnos.setName("Edgard Alva");
        alumnos.setAge(43);

        Integer idDocente = 2;
        Integer idAlumno = 1;

        DocentesAlumnos docentesAlumno = new DocentesAlumnos();
        docentesAlumno.setIdDocente(1);
        docentesAlumno.setIdAlumno(1);

        when(docentesRepository.save(docentes)).thenReturn(docentes);
        when(alumnosRepository.save(alumnos)).thenReturn(alumnos);

        when(docentesRepository.findById(idDocente)).thenReturn(Optional.of(docentes));
        when(alumnosRepository.findById(idAlumno)).thenReturn(Optional.of(alumnos));

        ResponseEntity<?> response = asignarDocentesAlumnosController.asignated(docentesAlumno);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void whenAsignated_thenNotFoundAlumno() {
        Docentes docentes = new Docentes();
        docentes.setId(1);
        docentes.setName("Alex Cobeñas");
        docentes.setAge(50);

        Alumnos alumnos = new Alumnos();
        alumnos.setId(1);
        alumnos.setName("Edgard Alva");
        alumnos.setAge(43);

        Integer idDocente = 1;
        Integer idAlumno = 2;

        DocentesAlumnos docentesAlumno = new DocentesAlumnos();
        docentesAlumno.setIdDocente(1);
        docentesAlumno.setIdAlumno(1);

        when(docentesRepository.save(docentes)).thenReturn(docentes);
        when(alumnosRepository.save(alumnos)).thenReturn(alumnos);

        when(docentesRepository.findById(idDocente)).thenReturn(Optional.of(docentes));
        when(alumnosRepository.findById(idAlumno)).thenReturn(Optional.of(alumnos));

        ResponseEntity<?> response = asignarDocentesAlumnosController.asignated(docentesAlumno);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void whenAsignated_thenFoundAlumnoDocente() {
        Docentes docentes = new Docentes();
        docentes.setId(1);
        docentes.setName("Alex Cobeñas");
        docentes.setAge(50);

        Alumnos alumnos = new Alumnos();
        alumnos.setId(1);
        alumnos.setName("Edgard Alva");
        alumnos.setAge(43);

        Integer idDocente = 1;
        Integer idAlumno = 1;

        DocentesAlumnos docentesAlumno = new DocentesAlumnos();
        docentesAlumno.setIdDocente(1);
        docentesAlumno.setIdAlumno(1);

        when(docentesRepository.save(docentes)).thenReturn(docentes);
        when(alumnosRepository.save(alumnos)).thenReturn(alumnos);

        when(docentesRepository.findById(idDocente)).thenReturn(Optional.of(docentes));
        when(alumnosRepository.findById(idAlumno)).thenReturn(Optional.of(alumnos));
        when(docentesAlumnosRepository.findByIdDocenteAndIdAlumno(idDocente, idAlumno)).thenReturn(Optional.of(docentesAlumno));

        ResponseEntity<?> response = asignarDocentesAlumnosController.asignated(docentesAlumno);
        assertEquals(response.getStatusCode(), HttpStatus.FOUND);
    }

    @Test
    void whenDesasignated_thenSuccess() {
        List<DocentesAlumnos> listDocente = new ArrayList<>();
        DocentesAlumnos docentesAlumnos = new DocentesAlumnos();
        docentesAlumnos.setIdDocente(1);
        docentesAlumnos.setIdAlumno(1);
        listDocente.add(docentesAlumnos);

        Integer idDocente = 1;
        Integer idAlumno = 1;

        DocentesAlumnos docentesAlumno = new DocentesAlumnos();
        docentesAlumno.setIdDocente(1);
        docentesAlumno.setIdAlumno(1);

        when(docentesAlumnosRepository.save(docentesAlumno)).thenReturn(docentesAlumno);

        when(docentesAlumnosRepository.findByIdDocente(idDocente)).thenReturn(listDocente);
        when(docentesAlumnosRepository.findByIdAlumno(idAlumno)).thenReturn(listDocente);
        when(docentesAlumnosRepository.findByIdDocenteAndIdAlumno(idDocente, idAlumno)).thenReturn(Optional.of(docentesAlumno));

        ResponseEntity<?> response = asignarDocentesAlumnosController.desasignated(docentesAlumno);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void whenDesasignated_thenNotFoundDocenteAlumno() {
        Integer idDocente = 1;
        Integer idAlumno = 1;

        DocentesAlumnos docentesAlumno = new DocentesAlumnos();

        when(docentesAlumnosRepository.findByIdDocenteAndIdAlumno(idDocente, idAlumno)).thenReturn(null);

        ResponseEntity<?> response = asignarDocentesAlumnosController.desasignated(docentesAlumno);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void whenGetAlumnoByDocente_thenSuccess() {
        List<DocentesAlumnos> listDocente = new ArrayList<>();
        DocentesAlumnos docentesAlumnos = new DocentesAlumnos();
        docentesAlumnos.setIdDocente(1);
        docentesAlumnos.setIdAlumno(1);
        listDocente.add(docentesAlumnos);

        Alumnos alumnos = new Alumnos();
        Integer idDocente = 1;

        DocentesAlumnos docentesAlumno = new DocentesAlumnos();
        docentesAlumno.setIdDocente(1);
        docentesAlumno.setIdAlumno(1);
        when(docentesAlumnosRepository.save(docentesAlumno)).thenReturn(docentesAlumno);

        when(docentesAlumnosRepository.findByIdDocente(idDocente)).thenReturn(listDocente);
        when(alumnosRepository.findById(docentesAlumnos.getIdAlumno())).thenReturn(Optional.of(alumnos));

        ResponseEntity<?> response = asignarDocentesAlumnosController.getAlumnoByDocente(idDocente);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void whenGetAlumnoByDocente_thenNotFoundDocente() {
        Alumnos alumnos = new Alumnos();
        Integer idDocente = 1;

        when(docentesAlumnosRepository.findByIdDocente(idDocente)).thenReturn(new ArrayList<>());
        when(alumnosRepository.findById(idDocente)).thenReturn(Optional.of(alumnos));

        ResponseEntity<?> response = asignarDocentesAlumnosController.getAlumnoByDocente(idDocente);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

}
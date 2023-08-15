package org.kodigo.library.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kodigo.library.models.Genre;
import org.kodigo.library.models.dto.GenreDTO;
import org.kodigo.library.service.IGenreService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class GenreControllerTest {

    @Mock
    private IGenreService genreService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private GenreController genreController;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<GenreDTO> genreDTOList = new ArrayList<>();

        when(genreService.getAll()).thenReturn(new ArrayList<>());
        when(mapper.map(any(Genre.class), eq(GenreDTO.class))).thenReturn(new GenreDTO());

        ResponseEntity<?> responseEntity = genreController.findAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testFindById() {
        Long genreId = 1L;
        GenreDTO genreDTO = new GenreDTO();
        Genre genre = new Genre();

        when(genreService.findById(genreId)).thenReturn(genre);
        when(mapper.map(genre, GenreDTO.class)).thenReturn(genreDTO);

        ResponseEntity<?> responseEntity = genreController.findById(genreId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testAdd() {
        GenreDTO genreDTO = new GenreDTO();
        Genre genre = new Genre();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mapper.map(genreDTO, Genre.class)).thenReturn(genre);
        when(genreService.add(genre)).thenReturn(genre);
        when(mapper.map(genre, GenreDTO.class)).thenReturn(genreDTO);

        ResponseEntity<?> responseEntity = genreController.add(genreDTO, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testAddWithError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = genreController.add(new GenreDTO(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testUpdate() {
        Long genreId = 1L;
        GenreDTO genreDTO = new GenreDTO();
        Genre genre = new Genre();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mapper.map(genreDTO, Genre.class)).thenReturn(genre);
        when(genreService.update(genre, genreId)).thenReturn(genre);
        when(mapper.map(genre, GenreDTO.class)).thenReturn(genreDTO);

        ResponseEntity<?> responseEntity = genreController.update(genreId, genreDTO, bindingResult);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateWithError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = genreController.update(1L, new GenreDTO(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testDelete() {
        Long genreId = 1L;

        ResponseEntity<?> responseEntity = genreController.delete(genreId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteWithError() {
        Long genreId = 1L;
        doThrow(new DataAccessException("Test error") {}).when(genreService).deleteLog(genreId);

        ResponseEntity<?> responseEntity = genreController.delete(genreId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}


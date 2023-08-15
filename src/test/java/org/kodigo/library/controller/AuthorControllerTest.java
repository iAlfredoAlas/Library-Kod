package org.kodigo.library.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kodigo.library.models.Author;
import org.kodigo.library.models.dto.AuthorDTO;
import org.kodigo.library.service.IAuthorService;
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

class AuthorControllerTest {

    @Mock
    private IAuthorService authorService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private AuthorController authorController;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<AuthorDTO> authorDTOList = new ArrayList<>();

        when(authorService.getAll()).thenReturn(new ArrayList<>());
        when(mapper.map(any(Author.class), eq(AuthorDTO.class))).thenReturn(new AuthorDTO());

        ResponseEntity<?> responseEntity = authorController.findAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testFindById() {
        Long authorId = 1L;
        AuthorDTO authorDTO = new AuthorDTO();
        Author author = new Author();

        when(authorService.findById(authorId)).thenReturn(author);
        when(mapper.map(author, AuthorDTO.class)).thenReturn(authorDTO);

        ResponseEntity<?> responseEntity = authorController.findById(authorId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testAdd() {
        AuthorDTO authorDTO = new AuthorDTO();
        Author author = new Author();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mapper.map(authorDTO, Author.class)).thenReturn(author);
        when(authorService.add(author)).thenReturn(author);
        when(mapper.map(author, AuthorDTO.class)).thenReturn(authorDTO);

        ResponseEntity<?> responseEntity = authorController.add(authorDTO, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testAddWithError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = authorController.add(new AuthorDTO(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testUpdate() {
        Long authorId = 1L;
        AuthorDTO authorDTO = new AuthorDTO();
        Author author = new Author();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mapper.map(authorDTO, Author.class)).thenReturn(author);
        when(authorService.update(author, authorId)).thenReturn(author);
        when(mapper.map(author, AuthorDTO.class)).thenReturn(authorDTO);

        ResponseEntity<?> responseEntity = authorController.update(authorId, authorDTO, bindingResult);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateWithError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = authorController.update(1L, new AuthorDTO(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testDelete() {
        Long authorId = 1L;

        ResponseEntity<?> responseEntity = authorController.delete(authorId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteWithError() {
        Long authorId = 1L;
        doThrow(new DataAccessException("Test error") {}).when(authorService).deleteLog(authorId);

        ResponseEntity<?> responseEntity = authorController.delete(authorId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}


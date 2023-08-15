package org.kodigo.library.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kodigo.library.models.Book;
import org.kodigo.library.models.dto.BookDTO;
import org.kodigo.library.service.IBookService;
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

class BookControllerTest {

    @Mock
    private IBookService bookService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private BookController bookController;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<BookDTO> bookDTOList = new ArrayList<>();

        when(bookService.getAll()).thenReturn(new ArrayList<>());
        when(mapper.map(any(Book.class), eq(BookDTO.class))).thenReturn(new BookDTO());

        ResponseEntity<?> responseEntity = bookController.findAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testFindById() {
        Long bookId = 1L;
        BookDTO bookDTO = new BookDTO();
        Book book = new Book();

        when(bookService.findById(bookId)).thenReturn(book);
        when(mapper.map(book, BookDTO.class)).thenReturn(bookDTO);

        ResponseEntity<?> responseEntity = bookController.findById(bookId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testAdd() {
        BookDTO bookDTO = new BookDTO();
        Book book = new Book();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mapper.map(bookDTO, Book.class)).thenReturn(book);
        when(bookService.add(book)).thenReturn(book);
        when(mapper.map(book, BookDTO.class)).thenReturn(bookDTO);

        ResponseEntity<?> responseEntity = bookController.add(bookDTO, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testAddWithError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = bookController.add(new BookDTO(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testUpdate() {
        Long bookId = 1L;
        BookDTO bookDTO = new BookDTO();
        Book book = new Book();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mapper.map(bookDTO, Book.class)).thenReturn(book);
        when(bookService.update(book, bookId)).thenReturn(book);
        when(mapper.map(book, BookDTO.class)).thenReturn(bookDTO);

        ResponseEntity<?> responseEntity = bookController.update(bookId, bookDTO, bindingResult);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateWithError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = bookController.update(1L, new BookDTO(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testDelete() {
        Long bookId = 1L;

        ResponseEntity<?> responseEntity = bookController.delete(bookId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteWithError() {
        Long bookId = 1L;
        doThrow(new DataAccessException("Test error") {}).when(bookService).deleteLog(bookId);

        ResponseEntity<?> responseEntity = bookController.delete(bookId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}


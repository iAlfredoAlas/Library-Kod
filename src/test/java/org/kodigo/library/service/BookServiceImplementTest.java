package org.kodigo.library.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kodigo.library.models.Book;
import org.kodigo.library.repository.IBookRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplementTest {

    @Mock
    private IBookRepository bookRepository;

    @InjectMocks
    private BookServiceImplement bookService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Book 1", new Date(), 200, 100, true, "A1", null, null, null));
        books.add(new Book(2L, "Book 2", new Date(), 150, 50, true, "B2", null, null, null));

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindBookById_ExistingBook() {
        Book book = new Book(1L, "Book 1", new Date(), 200, 100, true, "A1", null, null, null);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = bookService.findById(1L);

        assertEquals("Book 1", result.getNameBook());
    }

    @Test
    public void testFindBookById_NonExistingBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmptyResultDataAccessException.class, () -> bookService.findById(1L));
    }

    @Test
    public void testAddBook() {
        Book book = new Book(1L, "Book 1", new Date(), 200, 100, true, "A1", null, null, null);

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.add(book);

        assertEquals("Book 1", result.getNameBook());
    }

    @Test
    public void testUpdateBook() {
        Book existingBook = new Book(1L, "Book 1", new Date(), 200, 100, true, "A1", null, null, null);
        Book updatedBook = new Book(1L, "Updated Book", new Date(), 250, 120, false, "A2", null, null, null);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        Book result = bookService.update(updatedBook, 1L);

        assertEquals("Updated Book", result.getNameBook());
        assertEquals(250, (int) result.getTotalPage());
        assertEquals(false, result.getIsActiveBook());
    }

    @Test
    public void testDeleteBook() {
        Long bookId = 1L;

        bookService.deleteLog(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }
}


package org.kodigo.library.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kodigo.library.models.Author;
import org.kodigo.library.repository.IAuthorRepository;
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
public class AuthorServiceImplementTest {

    @Mock
    private IAuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImplement authorService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllAuthors() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1L, "Author 1", "Country 1", new Date(), true));
        authors.add(new Author(2L, "Author 2", "Country 2", new Date(), true));

        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> result = authorService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindAuthorById_ExistingAuthor() {
        Author author = new Author(1L, "Author 1", "Country 1", new Date(), true);

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Author result = authorService.findById(1L);

        assertEquals("Author 1", result.getNameAuthor());
    }

    @Test
    public void testFindAuthorById_NonExistingAuthor() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmptyResultDataAccessException.class, () -> authorService.findById(1L));
    }

    @Test
    public void testAddAuthor() {
        Author author = new Author(1L, "Author 1", "Country 1", new Date(), true);

        when(authorRepository.save(any(Author.class))).thenReturn(author);

        Author result = authorService.add(author);

        assertEquals("Author 1", result.getNameAuthor());
    }

    @Test
    public void testUpdateAuthor() {
        Author existingAuthor = new Author(1L, "Author 1", "Country 1", new Date(), true);
        Author updatedAuthor = new Author(1L, "Updated Author", "Country 1", new Date(), false);

        when(authorRepository.findById(1L)).thenReturn(Optional.of(existingAuthor));
        when(authorRepository.save(any(Author.class))).thenReturn(updatedAuthor);

        Author result = authorService.update(updatedAuthor, 1L);

        assertEquals("Updated Author", result.getNameAuthor());
        assertEquals(false, result.getIsActiveAuthor());
    }

    @Test
    public void testDeleteAuthor() {
        Long authorId = 1L;

        authorService.deleteLog(authorId);

        verify(authorRepository, times(1)).deleteById(authorId);
    }
}


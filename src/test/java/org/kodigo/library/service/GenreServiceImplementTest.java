package org.kodigo.library.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kodigo.library.models.Genre;
import org.kodigo.library.repository.IGenreRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GenreServiceImplementTest {

    @Mock
    private IGenreRepository genreRepository;

    @InjectMocks
    private GenreServiceImplement genreService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllGenres() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(1L, "Genre 1", true));
        genres.add(new Genre(2L, "Genre 2", true));

        when(genreRepository.findAll()).thenReturn(genres);

        List<Genre> result = genreService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindGenreById_ExistingGenre() {
        Genre genre = new Genre(1L, "Genre 1", true);

        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));

        Genre result = genreService.findById(1L);

        assertEquals("Genre 1", result.getNameGenre());
    }

    @Test
    public void testFindGenreById_NonExistingGenre() {
        when(genreRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmptyResultDataAccessException.class, () -> genreService.findById(1L));
    }

    @Test
    public void testAddGenre() {
        Genre genre = new Genre(1L, "Genre 1", true);

        when(genreRepository.save(any(Genre.class))).thenReturn(genre);

        Genre result = genreService.add(genre);

        assertEquals("Genre 1", result.getNameGenre());
    }

    @Test
    public void testUpdateGenre() {
        Genre existingGenre = new Genre(1L, "Genre 1", true);
        Genre updatedGenre = new Genre(1L, "Updated Genre", false);

        when(genreRepository.findById(1L)).thenReturn(Optional.of(existingGenre));
        when(genreRepository.save(any(Genre.class))).thenReturn(updatedGenre);

        Genre result = genreService.update(updatedGenre, 1L);

        assertEquals("Updated Genre", result.getNameGenre());
        assertEquals(false, result.getIsActiveGenre());
    }

    @Test
    public void testDeleteGenre() {
        Long genreId = 1L;

        genreService.deleteLog(genreId);

        verify(genreRepository, times(1)).deleteById(genreId);
    }

}

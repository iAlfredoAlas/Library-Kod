package org.kodigo.library.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kodigo.library.models.Editorial;
import org.kodigo.library.repository.IEditorialRepository;
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
public class EditorialServiceImplementTest {

    @Mock
    private IEditorialRepository editorialRepository;

    @InjectMocks
    private EditorialServiceImplement editorialService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllEditorials() {
        List<Editorial> editorials = new ArrayList<>();
        editorials.add(new Editorial(1L, "Editorial 1", new Date(), true));
        editorials.add(new Editorial(2L, "Editorial 2", new Date(), true));

        when(editorialRepository.findAll()).thenReturn(editorials);

        List<Editorial> result = editorialService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindEditorialById_ExistingEditorial() {
        Editorial editorial = new Editorial(1L, "Editorial 1", new Date(), true);

        when(editorialRepository.findById(1L)).thenReturn(Optional.of(editorial));

        Editorial result = editorialService.findById(1L);

        assertEquals("Editorial 1", result.getNameEditorial());
    }

    @Test
    public void testFindEditorialById_NonExistingEditorial() {
        when(editorialRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmptyResultDataAccessException.class, () -> editorialService.findById(1L));
    }

    @Test
    public void testAddEditorial() {
        Editorial editorial = new Editorial(1L, "Editorial 1", new Date(), true);

        when(editorialRepository.save(any(Editorial.class))).thenReturn(editorial);

        Editorial result = editorialService.add(editorial);

        assertEquals("Editorial 1", result.getNameEditorial());
    }

    @Test
    public void testUpdateEditorial() {
        Editorial existingEditorial = new Editorial(1L, "Editorial 1", new Date(), true);
        Editorial updatedEditorial = new Editorial(1L, "Updated Editorial", new Date(), false);

        when(editorialRepository.findById(1L)).thenReturn(Optional.of(existingEditorial));
        when(editorialRepository.save(any(Editorial.class))).thenReturn(updatedEditorial);

        Editorial result = editorialService.update(updatedEditorial, 1L);

        assertEquals("Updated Editorial", result.getNameEditorial());
        assertEquals(false, result.getIsActiveEditorial());
    }

    @Test
    public void testDeleteEditorial() {
        Long editorialId = 1L;

        editorialService.deleteLog(editorialId);

        verify(editorialRepository, times(1)).deleteById(editorialId);
    }

}

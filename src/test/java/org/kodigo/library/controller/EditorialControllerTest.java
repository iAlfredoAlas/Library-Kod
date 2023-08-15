package org.kodigo.library.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kodigo.library.models.Editorial;
import org.kodigo.library.models.dto.EditorialDTO;
import org.kodigo.library.service.IEditorialService;
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

class EditorialControllerTest {

    @Mock
    private IEditorialService editorialService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private EditorialController editorialController;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<EditorialDTO> editorialDTOList = new ArrayList<>();
        // Populate editorialDTOList with test data

        when(editorialService.getAll()).thenReturn(new ArrayList<>());
        when(mapper.map(any(Editorial.class), eq(EditorialDTO.class))).thenReturn(new EditorialDTO());

        ResponseEntity<?> responseEntity = editorialController.findAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testFindById() {
        Long editorialId = 1L;
        EditorialDTO editorialDTO = new EditorialDTO();
        Editorial editorial = new Editorial();

        when(editorialService.findById(editorialId)).thenReturn(editorial);
        when(mapper.map(editorial, EditorialDTO.class)).thenReturn(editorialDTO);

        ResponseEntity<?> responseEntity = editorialController.findById(editorialId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testAdd() {
        EditorialDTO editorialDTO = new EditorialDTO();
        Editorial editorial = new Editorial();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mapper.map(editorialDTO, Editorial.class)).thenReturn(editorial);
        when(editorialService.add(editorial)).thenReturn(editorial);
        when(mapper.map(editorial, EditorialDTO.class)).thenReturn(editorialDTO);

        ResponseEntity<?> responseEntity = editorialController.add(editorialDTO, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testAddWithError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = editorialController.add(new EditorialDTO(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testUpdate() {
        Long editorialId = 1L;
        EditorialDTO editorialDTO = new EditorialDTO();
        Editorial editorial = new Editorial();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mapper.map(editorialDTO, Editorial.class)).thenReturn(editorial);
        when(editorialService.update(editorial, editorialId)).thenReturn(editorial);
        when(mapper.map(editorial, EditorialDTO.class)).thenReturn(editorialDTO);

        ResponseEntity<?> responseEntity = editorialController.update(editorialId, editorialDTO, bindingResult);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateWithError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = editorialController.update(1L, new EditorialDTO(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testDelete() {
        Long editorialId = 1L;

        ResponseEntity<?> responseEntity = editorialController.delete(editorialId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteWithError() {
        Long editorialId = 1L;
        doThrow(new DataAccessException("Test error") {}).when(editorialService).deleteLog(editorialId);

        ResponseEntity<?> responseEntity = editorialController.delete(editorialId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}


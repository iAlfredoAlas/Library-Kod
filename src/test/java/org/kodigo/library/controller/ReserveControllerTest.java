package org.kodigo.library.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kodigo.library.models.Reserve;
import org.kodigo.library.models.dto.ReserveDTO;
import org.kodigo.library.service.IReserveService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ReserveControllerTest {

    @Mock
    private IReserveService reserveService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private ReserveController reserveController;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<ReserveDTO> reserveDTOList = new ArrayList<>();

        when(reserveService.getAll()).thenReturn(new ArrayList<>());
        when(mapper.map(any(Reserve.class), eq(ReserveDTO.class))).thenReturn(new ReserveDTO());

        ResponseEntity<?> responseEntity = reserveController.findAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testFindById() {
        Long reserveId = 1L;
        ReserveDTO reserveDTO = new ReserveDTO();
        Reserve reserve = new Reserve();

        when(reserveService.findById(reserveId)).thenReturn(reserve);
        when(mapper.map(reserve, ReserveDTO.class)).thenReturn(reserveDTO);

        ResponseEntity<?> responseEntity = reserveController.findById(reserveId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testAdd() {
        ReserveDTO reserveDTO = new ReserveDTO();
        Reserve reserve = new Reserve();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mapper.map(reserveDTO, Reserve.class)).thenReturn(reserve);
        when(reserveService.add(reserve)).thenReturn(reserve);
        when(mapper.map(reserve, ReserveDTO.class)).thenReturn(reserveDTO);

        ResponseEntity<?> responseEntity = reserveController.add(reserveDTO, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testAddWithError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = reserveController.add(new ReserveDTO(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testUpdate() {
        Long reserveId = 1L;
        ReserveDTO reserveDTO = new ReserveDTO();
        Reserve reserve = new Reserve();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mapper.map(reserveDTO, Reserve.class)).thenReturn(reserve);
        when(reserveService.update(reserve, reserveId)).thenReturn(reserve);
        when(mapper.map(reserve, ReserveDTO.class)).thenReturn(reserveDTO);

        ResponseEntity<?> responseEntity = reserveController.update(reserveId, reserveDTO, bindingResult);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateWithError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = reserveController.update(1L, new ReserveDTO(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testDelete() {
        Long reserveId = 1L;

        ResponseEntity<?> responseEntity = reserveController.delete(reserveId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteWithError() {
        Long reserveId = 1L;
        doThrow(new DataAccessException("Test error") {}).when(reserveService).deleteLog(reserveId);

        ResponseEntity<?> responseEntity = reserveController.delete(reserveId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}


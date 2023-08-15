package org.kodigo.library.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kodigo.library.models.Employee;
import org.kodigo.library.models.dto.EmployeeDTO;
import org.kodigo.library.service.IEmployeeService;
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

class EmployeeControllerTest {

    @Mock
    private IEmployeeService employeeService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        when(employeeService.getAll()).thenReturn(new ArrayList<>());
        when(mapper.map(any(Employee.class), eq(EmployeeDTO.class))).thenReturn(new EmployeeDTO());

        ResponseEntity<?> responseEntity = employeeController.findAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testFindById() {
        Long employeeId = 1L;
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = new Employee();

        when(employeeService.findById(employeeId)).thenReturn(employee);
        when(mapper.map(employee, EmployeeDTO.class)).thenReturn(employeeDTO);

        ResponseEntity<?> responseEntity = employeeController.findById(employeeId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testAdd() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = new Employee();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mapper.map(employeeDTO, Employee.class)).thenReturn(employee);
        when(employeeService.add(employee)).thenReturn(employee);
        when(mapper.map(employee, EmployeeDTO.class)).thenReturn(employeeDTO);

        ResponseEntity<?> responseEntity = employeeController.add(employeeDTO, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testAddWithError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = employeeController.add(new EmployeeDTO(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testUpdate() {
        Long employeeId = 1L;
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = new Employee();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mapper.map(employeeDTO, Employee.class)).thenReturn(employee);
        when(employeeService.update(employee, employeeId)).thenReturn(employee);
        when(mapper.map(employee, EmployeeDTO.class)).thenReturn(employeeDTO);

        ResponseEntity<?> responseEntity = employeeController.update(employeeId, employeeDTO, bindingResult);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateWithError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = employeeController.update(1L, new EmployeeDTO(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testDelete() {
        Long employeeId = 1L;

        ResponseEntity<?> responseEntity = employeeController.delete(employeeId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteWithError() {
        Long employeeId = 1L;
        doThrow(new DataAccessException("Test error") {}).when(employeeService).deleteLog(employeeId);

        ResponseEntity<?> responseEntity = employeeController.delete(employeeId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}


package org.kodigo.library.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.kodigo.library.models.Employee;
import org.kodigo.library.repository.IEmployeeRepository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceImplementTest {

    @Mock
    private IEmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImplement employeeService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Order(1)
    public void testGetAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "Employee 1", "E123", true));
        employees.add(new Employee(2L, "Employee 2", "E456", true));

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    @Order(2)
    public void testFindEmployeeById_ExistingEmployee() {
        Employee employee = new Employee(1L, "Employee 1", "E123", true);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.findById(1L);

        assertEquals("Employee 1", result.getNameEmployee());
    }

    @Test
    @Order(3)
    public void testFindEmployeeById_NonExistingEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmptyResultDataAccessException.class, () -> employeeService.findById(1L));
    }

    @Test
    @Order(4)
    public void testAddEmployee() {
        Employee employee = new Employee(1L, "Employee 1", "E123", true);

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee result = employeeService.add(employee);

        assertEquals("Employee 1", result.getNameEmployee());
    }

    @Test
    @Order(5)
    public void testUpdateEmployee() {
        Employee existingEmployee = new Employee(1L, "Employee 1", "E123", true);
        Employee updatedEmployee = new Employee(1L, "Updated Employee", "E456", false);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        Employee result = employeeService.update(updatedEmployee, 1L);

        assertEquals("Updated Employee", result.getNameEmployee());
        assertEquals("E456", result.getEmployeeNumber());
        assertEquals(false, result.getIsActiveEmployee());
    }

    @Test
    @Order(6)
    public void testDeleteEmployee() {
        Long employeeId = 1L;

        employeeService.deleteLog(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }
}


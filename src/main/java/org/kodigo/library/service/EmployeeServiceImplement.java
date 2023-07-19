package org.kodigo.library.service;

import lombok.extern.slf4j.Slf4j;
import org.kodigo.library.models.Employee;
import org.kodigo.library.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImplement implements IEmployeeService{

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        log.info("Show all data");
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findCustom(Boolean flat) {
        log.info("Show actives");
        return employeeRepository.findByIsActiveEmployee(flat);
    }

    @Override
    public Employee findById(Long id) {
        log.info("Show by Id");
        return employeeRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("No found", 404));
    }

    @Override
    public Employee add(Employee model) {
        log.info("Add Employee");
        return employeeRepository.save(model);
    }

    @Override
    public Employee update(Employee model, Long id) {
        log.info("Update info");
        Employee objEmployee = employeeRepository.findById(id).get();
        objEmployee.setNameEmployee(model.getNameEmployee());
        objEmployee.setIsActiveEmployee(model.getIsActiveEmployee());
        return employeeRepository.save(objEmployee);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Logical eliminated");
        employeeRepository.deleteById(id);
    }
}

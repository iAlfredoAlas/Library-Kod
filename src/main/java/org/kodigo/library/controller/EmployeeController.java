package org.kodigo.library.controller;

import lombok.extern.slf4j.Slf4j;
import org.kodigo.library.models.Employee;
import org.kodigo.library.models.dto.EmployeeDTO;
import org.kodigo.library.service.IEmployeeService;
import org.kodigo.library.utility.ResponseFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/library/employee")
public class EmployeeController implements ICrudGenericController<EmployeeDTO, Long>{
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseFactory.responseOk(employeeService.getAll().stream().map(mapEmployee -> mapper.map(mapEmployee, EmployeeDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> findCustom(Boolean filter) {
        return ResponseFactory.responseOk(employeeService.findCustom(filter).stream().map(mapEmployee -> mapper.map(mapEmployee, EmployeeDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        try {
            return ResponseFactory.responseOk(employeeService.findById(id));
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return ResponseFactory.responseNotFound("HTTP");
        }

    }

    @Override
    public ResponseEntity<?> add(EmployeeDTO model, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseFactory.responseError(errors);
        } else {
            try {
                return ResponseFactory
                        .responseCreated(employeeService.add(mapper.map(model, Employee.class)));
            } catch (DataAccessException e) {
                return ResponseFactory.responseNotFound(e.getMostSpecificCause().getMessage());
            }
        }

    }

    @Override
    public ResponseEntity<?> update(Long id, EmployeeDTO model, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseFactory.responseError(errors);
        } else {
            try {
                return ResponseFactory
                        .responseOk(employeeService.update(mapper.map(model, Employee.class), id));
            } catch (DataAccessException e) {
                log.error(e.getMessage());
                return ResponseFactory.responseNotFound(e.getMostSpecificCause().getMessage());
            }
        }

    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            employeeService.deleteLog(id);
            return ResponseFactory.responseOk();
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return ResponseFactory.responseNotFound(e.getMostSpecificCause().getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseFactory.responseGeneralError(e.getMessage());
        }

    }
}

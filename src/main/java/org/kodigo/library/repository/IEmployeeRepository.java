package org.kodigo.library.repository;

import org.kodigo.library.models.Employee;

import java.util.List;

public interface IEmployeeRepository extends IGenericRepository<Employee, Long> {

    List<Employee> findByIsActiveEmployee(Boolean isActiveEmployee);
}

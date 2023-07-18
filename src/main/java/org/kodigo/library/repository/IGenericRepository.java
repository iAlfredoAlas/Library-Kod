package org.kodigo.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGenericRepository<T, ID> extends JpaRepository<T, ID> {

    List<T> findByIsActive(Boolean isActive);

}

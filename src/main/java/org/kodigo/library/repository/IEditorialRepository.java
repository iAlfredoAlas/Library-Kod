package org.kodigo.library.repository;

import org.kodigo.library.models.Editorial;


import java.util.List;

public interface IEditorialRepository extends IGenericRepository<Editorial, Long>{
    List<Editorial> findByIsActiveEditorial(Boolean isActiveEditorial);
}

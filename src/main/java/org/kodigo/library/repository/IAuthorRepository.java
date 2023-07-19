package org.kodigo.library.repository;

import org.kodigo.library.models.Author;

import java.util.List;

public interface IAuthorRepository extends IGenericRepository<Author, Long> {

    List<Author> findByIsActiveAuthor(Boolean isActiveAuthor);
}

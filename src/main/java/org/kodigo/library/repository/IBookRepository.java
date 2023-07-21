package org.kodigo.library.repository;

import org.kodigo.library.models.Book;

import java.util.List;

public interface IBookRepository extends IGenericRepository<Book, Long>{
    List<Book> findByIsActiveBook(Boolean isActiveBook);
}

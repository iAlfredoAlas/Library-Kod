package org.kodigo.library.service;

import lombok.extern.slf4j.Slf4j;
import org.kodigo.library.models.Book;
import org.kodigo.library.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImplement implements IBookService{

    @Autowired
    private IBookRepository bookRepository;
    @Override
    public List<Book> getAll() {
        log.info("Show all data");
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findCustom(Boolean flat) {
        log.info("Show actives");
        return bookRepository.findByIsActiveBook(flat);
    }

    @Override
    public Book findById(Long id) {
        log.info("Show by Id");
        return bookRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Not found", 404));
    }

    @Override
    public Book add(Book model) {
        log.info("Add Book");
        return bookRepository.save(model);
    }

    @Override
    public Book update(Book model, Long id) {
        log.info("Update Book");
        Book objBook = bookRepository.findById(id).get();
        objBook.setNameBook(model.getNameBook());
        objBook.setPublicationDate(model.getPublicationDate());
        objBook.setTotalPage(model.getTotalPage());
        objBook.setQuantityStock(model.getQuantityStock());
        objBook.setIsActiveBook(model.getIsActiveBook());
        objBook.setPositionRack(model.getPositionRack());
        objBook.setIdAuthor(model.getIdAuthor());
        objBook.setIdEditorial(model.getIdEditorial());
        objBook.setIdGenre(model.getIdGenre());
        return bookRepository.save(objBook);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Logical eliminated");
        bookRepository.deleteById(id);
    }
}

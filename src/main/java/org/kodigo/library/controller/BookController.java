package org.kodigo.library.controller;

import lombok.extern.slf4j.Slf4j;
import org.kodigo.library.models.Author;
import org.kodigo.library.models.Book;
import org.kodigo.library.models.Genre;
import org.kodigo.library.models.dto.BookDTO;
import org.kodigo.library.service.IBookService;
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
@RequestMapping("/api/library/book")
public class BookController implements ICrudGenericController<BookDTO, Long> {

    @Autowired
    private IBookService bookService;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseFactory.responseOk(bookService.getAll().stream().map(mapBook -> mapper.map(mapBook, Book.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> findCustom(Boolean filter) {
        return ResponseFactory.responseOk(bookService.findCustom(filter).stream().map(mapBook -> mapper.map(mapBook, Book.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        try {
            return ResponseFactory.responseOk(bookService.findById(id));
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return ResponseFactory.responseNotFound("HTTP");
        }
    }

    @Override
    public ResponseEntity<?> add(BookDTO model, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseFactory.responseError(errors);
        } else {
            try {
                return ResponseFactory
                        .responseCreated(bookService.add(mapper.map(model, Book.class)));
            } catch (DataAccessException e) {
                return ResponseFactory.responseNotFound(e.getMostSpecificCause().getMessage());
            }
        }
    }

    @Override
    public ResponseEntity<?> update(Long id, BookDTO model, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseFactory.responseError(errors);
        } else {
            try {
                return ResponseFactory
                        .responseOk(bookService.update(mapper.map(model, Book.class), id));
            } catch (DataAccessException e) {
                log.error(e.getMessage());
                return ResponseFactory.responseNotFound(e.getMostSpecificCause().getMessage());
            }
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            bookService.deleteLog(id);
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

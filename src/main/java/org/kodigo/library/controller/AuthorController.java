package org.kodigo.library.controller;

import lombok.extern.slf4j.Slf4j;
import org.kodigo.library.models.Author;
import org.kodigo.library.models.dto.AuthorDTO;
import org.kodigo.library.service.IAuthorService;
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
@RequestMapping("/api/library/author")
public class AuthorController implements ICrudGenericController<AuthorDTO, Long> {

    @Autowired
    private IAuthorService authorService;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseFactory.responseOk(authorService.getAll().stream().map(mapAuthor -> mapper.map(mapAuthor, AuthorDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> findCustom(Boolean filter) {
        return ResponseFactory.responseOk(authorService.findCustom(filter).stream().map(mapAuthor -> mapper.map(mapAuthor, AuthorDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        try {
            return ResponseFactory.responseOk(authorService.findById(id));
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return ResponseFactory.responseNotFound("HTTP");
        }

    }

    @Override
    public ResponseEntity<?> add(AuthorDTO model, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseFactory.responseError(errors);
        } else {
            try {
                return ResponseFactory
                        .responseOk(authorService.add(mapper.map(model, Author.class)));
            } catch (DataAccessException e) {
                return ResponseFactory.responseNotFound(e.getMostSpecificCause().getMessage());
            }
        }

    }

    @Override
    public ResponseEntity<?> update(Long id, AuthorDTO model, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseFactory.responseError(errors);
        } else {
            try {
                return ResponseFactory
                        .responseOk(authorService.update(mapper.map(model, Author.class), id));
            } catch (DataAccessException e) {
                log.error(e.getMessage());
                return ResponseFactory.responseNotFound(e.getMostSpecificCause().getMessage());
            }
        }

    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            authorService.deleteLog(id);
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

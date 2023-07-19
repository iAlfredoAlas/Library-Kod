package org.kodigo.library.controller;



import lombok.extern.slf4j.Slf4j;
import org.kodigo.library.models.Editorial;
import org.kodigo.library.service.IEditorialService;
import org.kodigo.library.utility.ResponseFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.kodigo.library.models.dto.EditorialDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/library/editorial")
public class EditorialController implements ICrudGenericController<EditorialDTO, Long> {

    @Autowired
    private IEditorialService editorialService;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseFactory.responseOk(editorialService.getAll().stream().map(mapEditorial -> mapper.map(mapEditorial, Editorial.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> findCustom(Boolean filter) {
        return ResponseFactory.responseOk(editorialService.findCustom(filter).stream().map(mapEditorial -> mapper.map(mapEditorial, Editorial.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> findById(Long id) {

        try {
            return ResponseFactory.responseOk(editorialService.findById(id));
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return ResponseFactory.responseNotFound("HTTP");
        }
    }

    @Override
    public ResponseEntity<?> add(EditorialDTO model, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseFactory.responseError(errors);
        } else {
            try {
                return ResponseFactory
                        .responseOk(editorialService.add(mapper.map(model, Editorial.class)));
            } catch (DataAccessException e) {
                return ResponseFactory.responseNotFound(e.getMostSpecificCause().getMessage());
            }
        }

    }

    @Override
    public ResponseEntity<?> update(Long id, EditorialDTO model, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseFactory.responseError(errors);
        } else {
            try {
                return ResponseFactory
                        .responseOk(editorialService.update(mapper.map(model, Editorial.class), id));
            } catch (DataAccessException e) {
                log.error(e.getMessage());
                return ResponseFactory.responseNotFound(e.getMostSpecificCause().getMessage());
            }
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            editorialService.deleteLog(id);
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

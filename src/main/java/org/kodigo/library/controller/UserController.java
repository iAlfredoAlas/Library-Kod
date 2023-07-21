package org.kodigo.library.controller;

import lombok.extern.slf4j.Slf4j;
import org.kodigo.library.models.User;
import org.kodigo.library.models.dto.UserDTO;
import org.kodigo.library.service.IUserService;
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
@RequestMapping("/api/library/user")
public class UserController implements ICrudGenericController<UserDTO, Long> {

    @Autowired
    private IUserService userService;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseFactory.responseOk(userService.getAll().stream().map(mapAuthor -> mapper.map(mapAuthor, UserDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> findCustom(Boolean filter) {
        return ResponseFactory.responseOk(userService.findCustom(filter).stream().map(mapAuthor -> mapper.map(mapAuthor, UserDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        try {
            return ResponseFactory.responseOk(userService.findById(id));
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return ResponseFactory.responseNotFound("HTTP");
        }

    }

    @Override
    public ResponseEntity<?> add(UserDTO model, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseFactory.responseError(errors);
        } else {
            try {
                return ResponseFactory
                        .responseCreated(userService.add(mapper.map(model, User.class)));
            } catch (DataAccessException e) {
                return ResponseFactory.responseNotFound(e.getMostSpecificCause().getMessage());
            }
        }

    }

    @Override
    public ResponseEntity<?> update(Long id, UserDTO model, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseFactory.responseError(errors);
        } else {
            try {
                return ResponseFactory
                        .responseOk(userService.update(mapper.map(model, User.class), id));
            } catch (DataAccessException e) {
                log.error(e.getMessage());
                return ResponseFactory.responseNotFound(e.getMostSpecificCause().getMessage());
            }
        }

    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            userService.deleteLog(id);
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

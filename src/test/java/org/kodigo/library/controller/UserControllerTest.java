package org.kodigo.library.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kodigo.library.models.User;
import org.kodigo.library.models.dto.UserDTO;
import org.kodigo.library.service.IUserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private IUserService userService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private UserController userController;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<UserDTO> userDTOList = new ArrayList<>();
        // Populate userDTOList with test data

        when(userService.getAll()).thenReturn(new ArrayList<>());
        when(mapper.map(any(User.class), eq(UserDTO.class))).thenReturn(new UserDTO());

        ResponseEntity<?> responseEntity = userController.findAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testFindById() {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO();
        User user = new User();

        when(userService.findById(userId)).thenReturn(user);
        when(mapper.map(user, UserDTO.class)).thenReturn(userDTO);

        ResponseEntity<?> responseEntity = userController.findById(userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testAdd() {
        UserDTO userDTO = new UserDTO();
        User user = new User();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mapper.map(userDTO, User.class)).thenReturn(user);
        when(userService.add(user)).thenReturn(user);
        when(mapper.map(user, UserDTO.class)).thenReturn(userDTO);

        ResponseEntity<?> responseEntity = userController.add(userDTO, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testAddWithError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = userController.add(new UserDTO(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testUpdate() {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO();
        User user = new User();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mapper.map(userDTO, User.class)).thenReturn(user);
        when(userService.update(user, userId)).thenReturn(user);
        when(mapper.map(user, UserDTO.class)).thenReturn(userDTO);

        ResponseEntity<?> responseEntity = userController.update(userId, userDTO, bindingResult);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateWithError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = userController.update(1L, new UserDTO(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testDelete() {
        Long userId = 1L;

        ResponseEntity<?> responseEntity = userController.delete(userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteWithError() {
        Long userId = 1L;
        doThrow(new DataAccessException("Test error") {}).when(userService).deleteLog(userId);

        ResponseEntity<?> responseEntity = userController.delete(userId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}


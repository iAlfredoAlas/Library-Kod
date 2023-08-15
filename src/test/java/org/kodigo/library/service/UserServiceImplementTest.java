package org.kodigo.library.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.kodigo.library.models.User;
import org.kodigo.library.repository.IUserRepository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplementTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserServiceImplement userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "User 1", "C123", "user1@example.com", "123456789", true));
        users.add(new User(2L, "User 2", "C456", "user2@example.com", "987654321", true));

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindUserById_ExistingUser() {
        User user = new User(1L, "User 1", "C123", "user1@example.com", "123456789", true);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.findById(1L);

        assertEquals("User 1", result.getNameUser());
    }

    @Test
    public void testFindUserById_NonExistingUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmptyResultDataAccessException.class, () -> userService.findById(1L));
    }

    @Test
    public void testAddUser() {
        User user = new User(1L, "User 1", "C123", "user1@example.com", "123456789", true);

        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.add(user);

        assertEquals("User 1", result.getNameUser());
    }

    @Test
    public void testUpdateUser() {
        User existingUser = new User(1L, "User 1", "C123", "user1@example.com", "123456789", true);
        User updatedUser = new User(1L, "Updated User", "C456", "updated@example.com", "987654321", false);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.update(updatedUser, 1L);

        assertEquals("Updated User", result.getNameUser());
        assertEquals("C456", result.getCarnetUser());
        assertEquals("updated@example.com", result.getEmailUser());
        assertEquals("987654321", result.getPhoneUser());
        assertEquals(false, result.getIsActiveUser());
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        userService.deleteLog(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}


package org.kodigo.library.service;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.kodigo.library.models.Book;
import org.kodigo.library.models.Employee;
import org.kodigo.library.models.Reserve;
import org.kodigo.library.models.User;
import org.kodigo.library.repository.IReserveRepository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReserveServiceImplementTest {

    @Mock
    private IReserveRepository reserveRepository;

    @InjectMocks
    private ReserveServiceImplement reserveService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllReserves() {
        List<Reserve> reserves = new ArrayList<>();
        reserves.add(new Reserve(1L, new Date(), true, new Book(), new Employee(), new User()));
        reserves.add(new Reserve(2L, new Date(), true, new Book(), new Employee(), new User()));

        when(reserveRepository.findAll()).thenReturn(reserves);

        List<Reserve> result = reserveService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindReserveById_ExistingReserve() {
        Reserve reserve = new Reserve(1L, new Date(), true, new Book(), new Employee(), new User());

        when(reserveRepository.findById(1L)).thenReturn(Optional.of(reserve));

        Reserve result = reserveService.findById(1L);

        assertEquals(true, result.getIsActiveReserve());
    }

    @Test
    public void testFindReserveById_NonExistingReserve() {
        when(reserveRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmptyResultDataAccessException.class, () -> reserveService.findById(1L));
    }

    @Test
    public void testAddReserve() {
        Reserve reserve = new Reserve(1L, new Date(), true, new Book(), new Employee(), new User());

        when(reserveRepository.save(any(Reserve.class))).thenReturn(reserve);

        Reserve result = reserveService.add(reserve);

        assertEquals(true, result.getIsActiveReserve());
    }

    @Test
    public void testUpdateReserve() {
        Reserve existingReserve = new Reserve(1L, new Date(), true, new Book(), new Employee(), new User());
        Reserve updatedReserve = new Reserve(1L, new Date(), false, new Book(), new Employee(), new User());

        when(reserveRepository.findById(1L)).thenReturn(Optional.of(existingReserve));
        when(reserveRepository.save(any(Reserve.class))).thenReturn(updatedReserve);

        Reserve result = reserveService.update(updatedReserve, 1L);

        assertEquals(false, result.getIsActiveReserve());
    }

    @Test
    public void testDeleteReserve() {
        Long reserveId = 1L;

        reserveService.deleteLog(reserveId);

        verify(reserveRepository, times(1)).deleteById(reserveId);
    }
}


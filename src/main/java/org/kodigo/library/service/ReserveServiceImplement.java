package org.kodigo.library.service;

import lombok.extern.slf4j.Slf4j;
import org.kodigo.library.models.Reserve;
import org.kodigo.library.repository.IReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReserveServiceImplement implements IReserveService{

    @Autowired
    private IReserveRepository reserveRepository;
    @Override
    public List<Reserve> getAll() {
        log.info("Show all data");
        return reserveRepository.findAll();
    }

    @Override
    public List<Reserve> findCustom(Boolean flat) {
        log.info("Show actives");
        return reserveRepository.findByIsActiveReserve(flat);
    }

    @Override
    public Reserve findById(Long id) {
        log.info("Show by Id");
        return reserveRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("No found", 404));
    }

    @Override
    public Reserve add(Reserve model) {
        log.info("Add Reserve");
        return reserveRepository.save(model);
    }

    @Override
    public Reserve update(Reserve model, Long id) {
        log.info("Update Reserve");
        Reserve objReserve = reserveRepository.findById(id).get();
        objReserve.setDateReserve(model.getDateReserve());
        objReserve.setIsActiveReserve(model.getIsActiveReserve());
        objReserve.setIdBook(model.getIdBook());
        objReserve.setIdEmployee(model.getIdEmployee());
        objReserve.setIdUser(model.getIdUser());
        return reserveRepository.save(objReserve);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Logical eliminated");
        reserveRepository.deleteById(id);
    }
}

package org.kodigo.library.service;

import org.kodigo.library.models.Editorial;
import org.kodigo.library.repository.IEditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;


import java.util.List;
@Slf4j
@Service
public class EditorialServiceImplement implements  IEditorialService{

    @Autowired
    private IEditorialRepository editorialRepository;


    @Override
    public List<Editorial> getAll() {
        log.info("Show all data");
       return editorialRepository.findAll();
    }

    @Override
    public List<Editorial> findCustom(Boolean flat) {
        log.info("Show actives");
        return editorialRepository.findByIsActiveEditorial(flat);
    }

    @Override
    public Editorial findById(Long id) {
        log.info("Show by Id");
        return editorialRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("No found", 404));
    }

    @Override
    public Editorial add(Editorial model) {

        log.info("Add Author");
        return editorialRepository.save(model);
    }

    @Override
    public Editorial update(Editorial model, Long id) {

        log.info("Update info");
        Editorial objEditorial = editorialRepository.findById(id).get();
        objEditorial.setNameEditorial(model.getNameEditorial());
        objEditorial.setDateAdd(model.getDateAdd());
        objEditorial.setIsActiveEditorial(model.getIsActiveEditorial());
        return editorialRepository.save(objEditorial);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Logical eliminated");
        editorialRepository.deleteById(id);
    }
}

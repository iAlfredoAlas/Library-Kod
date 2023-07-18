package org.kodigo.library.service;

import lombok.extern.slf4j.Slf4j;
import org.kodigo.library.models.Genre;
import org.kodigo.library.repository.IGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GenreServiceImplement implements IGenreService {

    @Autowired
    private IGenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {
        log.info("Show all data");
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> findCustom(Boolean flat) {
        log.info("Show actives");
        return genreRepository.findByIsActive(flat);
    }

    @Override
    public Genre findById(Long id) {
        log.info("Show by Id");
        return genreRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("No found", 404));
    }

    @Override
    public Genre add(Genre model) {
        log.info("Add Genre");
        return genreRepository.save(model);
    }

    @Override
    public Genre update(Genre model, Long id) {
        log.info("Update info");
        Genre objGenre = genreRepository.findById(id).get();
        objGenre.setNameGenre(model.getNameGenre());
        objGenre.setIsActiveGenre(model.getIsActiveGenre());
        return genreRepository.save(objGenre);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Logical eliminated");
        genreRepository.deleteById(id);
    }
}

package org.kodigo.library.service;

import lombok.extern.slf4j.Slf4j;
import org.kodigo.library.models.Author;
import org.kodigo.library.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AuthorServiceImplement implements IAuthorService {

    @Autowired
    private IAuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        log.info("Show all data");
        return authorRepository.findAll();
    }

    @Override
    public List<Author> findCustom(Boolean flat) {
        log.info("Show actives");
        return authorRepository.findByIsActiveAuthor(flat);
    }

    @Override
    public Author findById(Long id) {
        log.info("Show by Id");
        return authorRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("No found", 404));
    }

    @Override
    public Author add(Author model) {
        log.info("Add Author");
        return authorRepository.save(model);
    }

    @Override
    public Author update(Author model, Long id) {
        log.info("Update info");
        Author objAuthor = authorRepository.findById(id).get();
        objAuthor.setNameAuthor(model.getNameAuthor());
        objAuthor.setIsActiveAuthor(model.getIsActiveAuthor());
        return authorRepository.save(objAuthor);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Logical eliminated");
        authorRepository.deleteById(id);
    }
}


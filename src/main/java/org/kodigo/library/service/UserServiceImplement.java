package org.kodigo.library.service;

import lombok.extern.slf4j.Slf4j;
import org.kodigo.library.models.User;
import org.kodigo.library.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImplement implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> getAll() {
        log.info("Show all data");
        return userRepository.findAll();
    }

    @Override
    public List<User> findCustom(Boolean flat) {
        log.info("Show actives");
        return userRepository.findByIsActiveUser(flat);
    }

    @Override
    public User findById(Long id) {
        log.info("Show by Id");
        return userRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("No found", 404));
    }

    @Override
    public User add(User model) {
        log.info("Add Genre");
        return userRepository.save(model);
    }

    @Override
    public User update(User model, Long id) {
        log.info("Update info");
        User objUser = userRepository.findById(id).get();
        objUser.setNameUser(model.getNameUser());
        objUser.setIsActiveUser(model.getIsActiveUser());
        return userRepository.save(objUser);
    }

    @Override
    public void deleteLog(Long id) {
        log.info("Logical eliminated");
        userRepository.deleteById(id);
    }
}

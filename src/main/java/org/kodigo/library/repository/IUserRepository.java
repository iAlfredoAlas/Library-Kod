package org.kodigo.library.repository;

import org.kodigo.library.models.User;

import java.util.List;

public interface IUserRepository extends IGenericRepository<User, Long> {

    List<User> findByIsActiveUser(Boolean isActiveUser);
}

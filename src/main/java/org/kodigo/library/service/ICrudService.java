package org.kodigo.library.service;

import java.util.List;

public interface ICrudService<T, ID> {

    //List all
    public List<T> getAll();

    //List active
    public List<T> findCustom(Boolean flat);

    //List by id
    public T findById(Long id);

    //Add
    public T add(T model);

    //Update
    public T update(T model, Long id);

    //Logical eliminated
    public void deleteLog(Long id);

}

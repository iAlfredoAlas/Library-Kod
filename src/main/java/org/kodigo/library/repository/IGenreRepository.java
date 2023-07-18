package org.kodigo.library.repository;

import org.kodigo.library.models.Genre;

import java.util.List;

public interface IGenreRepository extends IGenericRepository<Genre, Long> {

    List<Genre> findByIsActiveGenre(Boolean isActiveGenre);
}

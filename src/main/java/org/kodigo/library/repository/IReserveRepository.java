package org.kodigo.library.repository;

import org.kodigo.library.models.Reserve;

import java.util.List;

public interface IReserveRepository extends IGenericRepository<Reserve,Long> {
    List<Reserve> findByIsActiveReserve(Boolean isActiveReserve);
}

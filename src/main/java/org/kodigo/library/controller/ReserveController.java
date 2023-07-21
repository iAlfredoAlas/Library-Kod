package org.kodigo.library.controller;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.kodigo.library.models.Book;
import org.kodigo.library.models.Reserve;
import org.kodigo.library.models.dto.ReserveDTO;
import org.kodigo.library.service.IReserveService;
import org.kodigo.library.service.ReportService;
import org.kodigo.library.utility.ResponseFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/library/reserve")
public class ReserveController implements ICrudGenericController<ReserveDTO, Long> {

    @Autowired
    private IReserveService reserveService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ReportService reportService;

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseFactory.responseOk(reserveService.getAll().stream().map(mapReserve -> mapper.map(mapReserve, Reserve.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> findCustom(Boolean filter) {
        return ResponseFactory.responseOk(reserveService.findCustom(filter).stream().map(mapReserve -> mapper.map(mapReserve, Reserve.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        try {
            return ResponseFactory.responseOk(reserveService.findById(id));
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return ResponseFactory.responseNotFound("HTTP");
        }
    }

    @Override
    public ResponseEntity<?> add(ReserveDTO model, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseFactory.responseError(errors);
        } else {
            try {
                return ResponseFactory
                        .responseCreated(reserveService.add(mapper.map(model, Reserve.class)));
            } catch (DataAccessException e) {
                return ResponseFactory.responseNotFound(e.getMostSpecificCause().getMessage());
            }
        }
    }

    @Override
    public ResponseEntity<?> update(Long id, ReserveDTO model, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseFactory.responseError(errors);
        } else {
            try {
                return ResponseFactory
                        .responseOk(reserveService.update(mapper.map(model, Reserve.class), id));
            } catch (DataAccessException e) {
                log.error(e.getMessage());
                return ResponseFactory.responseNotFound(e.getMostSpecificCause().getMessage());
            }
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            reserveService.deleteLog(id);
            return ResponseFactory.responseOk();
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return ResponseFactory.responseNotFound(e.getMostSpecificCause().getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseFactory.responseGeneralError(e.getMessage());
        }
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportReport(format);
    }

}

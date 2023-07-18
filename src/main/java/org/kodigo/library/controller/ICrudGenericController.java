package org.kodigo.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ICrudGenericController<T, ID> {

    @GetMapping("/all")
    public ResponseEntity<?> findAll();

    // Obtiene activos
    @GetMapping("/find/active/{filter}")
    public ResponseEntity<?> findCustom(@PathVariable Boolean filter);

    // Obtiene por id
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable ID id);

    // Agrega departamento
    @PostMapping("/save")
    public ResponseEntity<?> add(@Valid @RequestBody T model, BindingResult errors);

    // Actualiza por id
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable ID id, @Valid @RequestBody T model, BindingResult errors);

    // Elimina por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable ID id);


}

package org.kodigo.library.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ResponseFactory {

    public static ResponseEntity<?> responseOk() {
        Map<String, Object> res = new HashMap<>();
        res.put("HTTP", HttpStatus.OK);
        res.put("Mensaje", "Operación realizada con éxito");
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    public static ResponseEntity<?> responseOk(Object data) {
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("HTTP", HttpStatus.OK);
        res.put("Mensaje", "Operación realizada con éxito");
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    public static ResponseEntity<?> responseOk(Object data, String mensaje) {
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("HTTP", HttpStatus.OK);
        res.put("Mensaje", mensaje);
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    public static ResponseEntity<?> responseError(BindingResult error) {
        Map<String, Object> res = new HashMap<>();
        Map<String, String> errorList = error.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        res.put("HTTP", HttpStatus.BAD_REQUEST);
        res.put("Mensaje", "Error interno");
        res.put("FieldError", errorList);
        return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> responseNotFound(Object data) {
        Map<String, Object> res = new HashMap<>();
        res.put("HTTP", HttpStatus.NOT_FOUND);
        res.put("Mensaje", "Error interno");
        res.put("Error", data);
        return new ResponseEntity<Object>(res, HttpStatus.NOT_FOUND);
    }
    public static ResponseEntity<?> responseGeneralError(String error) {
        Map<String, Object> res = new HashMap<>();
        res.put("HTTP", HttpStatus.INTERNAL_SERVER_ERROR);
        res.put("Mensaje", "Error interno");
        res.put("Error", error);
        return new ResponseEntity<Object>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

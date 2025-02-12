package com.alura.foro_hub.infra.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class TratadorErrores {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> tratarErrorDuplicate(SQLIntegrityConstraintViolationException e){
        String mensaje = e.getMessage();
        return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> tratarError400(MethodArgumentNotValidException e){
        String mensaje = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList().toString();
        return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> tratarValorNoPresenteEnLaLista(NoSuchElementException e) {
        String mensaje = "Valor no encontrado, ingresar otro id. Detalle: " + e.getMessage();
        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> tratarSoporteMetodoPut(HttpRequestMethodNotSupportedException e) {
        String mensaje = "El método put no soporta la url ingresada. Detalle: " + e.getMessage();
        return new ResponseEntity<>(mensaje, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> tratarIdNoNull(IllegalArgumentException e){
        String mensaje = e.getMessage();
        return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
    }

    public record DatosErrorValidacion(String campo, String error) {
        public DatosErrorValidacion(FieldError e){
            this(e.getField(), e.getDefaultMessage());
        }
    }
}

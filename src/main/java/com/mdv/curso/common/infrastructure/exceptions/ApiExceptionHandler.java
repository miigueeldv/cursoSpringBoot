package com.mdv.curso.common.infrastructure.exceptions;

import com.mdv.curso.product.domain.exception.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //Anotación para controlar excepciones HTTP
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
                errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ErrorMessage(ex.getMessage(),ex.getClass().getSimpleName(),request.getRequestURI(),errors);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    public ErrorMessage notFound(HttpServletRequest request, Exception ex) {
        return new ErrorMessage(ex.getMessage(),ex.getClass().getSimpleName(),request.getRequestURI());
    }
}

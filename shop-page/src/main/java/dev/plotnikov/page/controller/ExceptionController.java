package dev.plotnikov.page.controller;

import dev.plotnikov.page.exception.MigrationFailedException;
import dev.plotnikov.page.exception.StorageException;
import dev.plotnikov.page.exception.StorageFileNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception, Model model,
                                               HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", exception.getMessage());
        return "errors/404";
    }

    @ExceptionHandler(StorageException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchElementException(Model model) {
        model.addAttribute("isNotUpload", true);
        return "index";
    }

    @ExceptionHandler(MigrationFailedException.class)
    public String migrationFailedException(Model model, MigrationFailedException exception) {
        model.addAttribute("migrationFailed", exception.getMessage());
        return "index";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public String handleException() {
        return "errors/404";
    }
}
package dev.plotnikov.page.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomBadRequestException extends RuntimeException {

    private final List<String> errors;

    public CustomBadRequestException(List<String> errors) {
        this.errors = errors;
    }

}

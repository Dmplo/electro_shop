package dev.plotnikov.rest.controller;

import dev.plotnikov.rest.entity.ElectroTypeEmployee;
import dev.plotnikov.rest.service.ElectroTypeEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/electrotype-employees")
@Tag(name = "ElectroTypeEmployee", description = "API для работы с привязкой типа электротовара к сотруднику")
public class ElectroTypeEmployeeController {

private final ElectroTypeEmployeeService service;

    @Operation(
            summary = "Сохранить во время выполнения миграции",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "201", content = @Content(schema = @Schema())),
                    @ApiResponse(description = "Внутренняя ошибка", responseCode = "500", content = @Content(schema = @Schema()))
            }
    )
    @PostMapping("migration")
    public ResponseEntity<?> migration(@Valid @RequestBody List<ElectroTypeEmployee> electroTypeEmployees, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            service.saveAll(electroTypeEmployees);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

}

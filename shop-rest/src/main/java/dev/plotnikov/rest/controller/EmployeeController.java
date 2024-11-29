package dev.plotnikov.rest.controller;

import dev.plotnikov.rest.entity.DTO.EmployeeDTO;
import dev.plotnikov.rest.entity.Employee;
import dev.plotnikov.rest.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employees")
@Tag(name = "Employee", description = "API для работы с сотрудниками")
public class EmployeeController {

    private final EmployeeService service;

    @Operation(
            summary = "Получить информацию о всех сотрудниках",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = EmployeeDTO.class)))
            }
    )
    @GetMapping
    public List<EmployeeDTO> getAll() {
        return service.getAllCustom();
    }

    @Operation(
            summary = "Сохранить во время выполнения миграции",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "201", content = @Content(schema = @Schema())),
                    @ApiResponse(description = "Внутренняя ошибка", responseCode = "500", content = @Content(schema = @Schema()))
            }
    )
    @PostMapping("migration")
    public ResponseEntity<?> migration(@Valid @RequestBody List<Employee> employees, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            service.saveAll(employees);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @Operation(
            summary = "Получить сотрудника по идентификатору",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = EmployeeDTO.class))),
                    @ApiResponse(description = "Сотрудник не найден", responseCode = "404", content = @Content(schema = @Schema()))
            }
    )
    @GetMapping("{id:\\d+}")
    public EmployeeDTO getById(@PathVariable("id") Long id) {
        return service.getByIdCustom(id);
    }


    @Operation(
            summary = "Обновить данные сотрудника",
            responses = {
                    @ApiResponse(description = "Успешно обновлен", responseCode = "204", content = @Content(schema = @Schema())),
                    @ApiResponse(description = "Сотрудник не найден", responseCode = "404", content = @Content(schema = @Schema())),
                    @ApiResponse(description = "Внутренняя ошибка", responseCode = "500", content = @Content(schema = @Schema()))
            }
    )
    @PutMapping("update")
    public ResponseEntity<?> update(@Valid @RequestBody Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Запрос содержит ошибки");
            problemDetail.setProperty("errors", result.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList());
            return ResponseEntity.badRequest().body(problemDetail);
        } else {
            service.update(employee);
            return ResponseEntity.noContent().build();
        }
    }
}

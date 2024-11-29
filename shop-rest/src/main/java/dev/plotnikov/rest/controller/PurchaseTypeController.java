package dev.plotnikov.rest.controller;

import dev.plotnikov.rest.entity.PurchaseType;
import dev.plotnikov.rest.service.PurchaseTypeService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/pays")
@Tag(name = "PurchaseType", description = "API для работы с типами покупки")
public class PurchaseTypeController {

private final PurchaseTypeService service;

    @Operation(
            summary = "Получить информацию о всех типах",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = PurchaseType.class)))
            }
    )
    @GetMapping
    public List<PurchaseType> getAll() {
        return service.getAll();
    }

    @Operation(
            summary = "Сохранить во время выполнения миграции",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "201", content = @Content(schema = @Schema())),
                    @ApiResponse(description = "Внутренняя ошибка", responseCode = "500", content = @Content(schema = @Schema()))
            }
    )
    @PostMapping("migration")
    public ResponseEntity<?> migration(@Valid @RequestBody List<PurchaseType> purchaseTypes, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            service.saveAll(purchaseTypes);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
    
}

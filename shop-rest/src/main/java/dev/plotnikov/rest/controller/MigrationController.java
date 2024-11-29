package dev.plotnikov.rest.controller;

import dev.plotnikov.rest.service.MigrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Migration", description = "API для работы с миграцией случайными данными")
public class MigrationController {

    private final MigrationService service;

    @Operation(
            summary = "Запустить заполнение случайными данными",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "201", content = @Content(schema = @Schema())),
                    @ApiResponse(description = "Внутренняя ошибка", responseCode = "500", content = @Content(schema = @Schema()))
            }
    )
    @GetMapping("api/migration/random")
    public ResponseEntity<Void> startRandomMigration() {
        service.getRandomMigration();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

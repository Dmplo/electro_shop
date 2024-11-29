package dev.plotnikov.rest.controller;

import dev.plotnikov.rest.entity.DTO.ElectroItemPageDTO;
import dev.plotnikov.rest.entity.DTO.ElectroShopCount.ElectroItemDTO;
import dev.plotnikov.rest.entity.ElectroItem;
import dev.plotnikov.rest.service.ElectroItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
@Tag(name = "ElectroItem", description = "API для работы с продуктами")
public class ElectroItemController {

    private final ElectroItemService service;

    @Operation(
            summary = "Получить информацию о всех продуктах",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = ElectroItemPageDTO.class)))
            }
    )
    @GetMapping
    public ElectroItemPageDTO getAll(
            @RequestParam(name = "type", required = false) Long typeId,
            @PageableDefault(size = 8, sort = {"count"}, direction = Sort.Direction.DESC) Pageable pageable
            ) {
        return service.getAll(typeId, pageable);
    }

    @Operation(
            summary = "Сохранить во время выполнения миграции",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "201", content = @Content(schema = @Schema())),
                    @ApiResponse(description = "Внутренняя ошибка", responseCode = "500", content = @Content(schema = @Schema()))
            }
    )
    @PostMapping("migration")
    public ResponseEntity<?> migration(@Valid @RequestBody List<ElectroItem> electroItems, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            service.saveAll(electroItems);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @Operation(
            summary = "Получить продукт по идентификатору",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = ElectroItemDTO.class))),
                    @ApiResponse(description = "Продукт не найден", responseCode = "404", content = @Content(schema = @Schema()))
            }
    )
    @GetMapping("{id:\\d+}")
    public ElectroItemDTO getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

}

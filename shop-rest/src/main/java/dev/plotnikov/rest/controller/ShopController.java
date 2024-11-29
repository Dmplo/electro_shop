package dev.plotnikov.rest.controller;

import dev.plotnikov.rest.entity.DTO.ElectroShopCount.ElectroItemDTO;
import dev.plotnikov.rest.entity.ElectroType;
import dev.plotnikov.rest.entity.Shop;
import dev.plotnikov.rest.service.ShopService;
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
@RequestMapping("api/shops")
@Tag(name = "Shop", description = "API для работы с магазинами")
public class ShopController {

private final ShopService service;

    @Operation(
            summary = "Получить информацию о всех магазиных",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = Shop.class)))
            }
    )
    @GetMapping
    public List<Shop> getAll() {
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
    public ResponseEntity<?> migration(@Valid @RequestBody List<Shop> shops, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            service.saveAll(shops);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @Operation(
            summary = "Получить магазин по идентификатору",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = Shop.class))),
                    @ApiResponse(description = "Магазин не найден", responseCode = "404", content = @Content(schema = @Schema()))
            }
    )
    @GetMapping("{id:\\d+}")
    public Shop getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @Operation(
            summary = "Обновить данные магазина",
            responses = {
                    @ApiResponse(description = "Успешно обновлен", responseCode = "204", content = @Content(schema = @Schema())),
                    @ApiResponse(description = "Магазин не найден", responseCode = "404", content = @Content(schema = @Schema())),
                    @ApiResponse(description = "Внутренняя ошибка", responseCode = "500", content = @Content(schema = @Schema()))
            }
    )
    @PutMapping("update")
    public ResponseEntity<?> update(@Valid @RequestBody Shop shop, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            service.update(shop);
            return ResponseEntity.noContent().build();
        }
    }
}

package dev.plotnikov.rest.controller;

import dev.plotnikov.rest.controller.params.RequestParams;
import dev.plotnikov.rest.entity.DTO.*;
import dev.plotnikov.rest.entity.Purchase;
import dev.plotnikov.rest.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/purchases")
@Tag(name = "Purchase", description = "API для работы с покупками")
public class PurchaseController {

    private final PurchaseService service;

    @Operation(
            summary = "Получить информацию о всех покупках",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = PurchaseAllPageDTO.class)))
            }
    )
    @PostMapping
    public PurchaseAllPageDTO getAll(
            @RequestBody RequestParams params,
            @PageableDefault(size = 13,sort = {"purchase_date"}) Pageable pageable) {
        return service.getAll(params, pageable);
    }

    @Operation(
            summary = "Получить информацию о количестве покупок",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = Long.class)))
            }
    )
    @GetMapping("count")
    public Long getCount() {
        return service.getCount();
    }

    @Operation(
            summary = "Сохранить во время выполнения миграции",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "201", content = @Content(schema = @Schema())),
                    @ApiResponse(description = "Внутренняя ошибка", responseCode = "500", content = @Content(schema = @Schema()))
            }
    )
    @PostMapping("migration")
    public ResponseEntity<?> migration(@Valid @RequestBody List<Purchase> purchases, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            service.saveAll(purchases);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @Operation(
            summary = "Получить информацию по фильтру рейтинг менеджеров по сумме проданных товаров",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = PurchaseRatingAmountDTO.class)))
            }
    )
    @PostMapping("rating/amount")
    public List<PurchaseRatingAmountDTO> getAmountRating(@RequestBody RequestParams params) {
        return service.getAmountRating(params);
    }

    @Operation(
            summary = "Получить информацию по фильтру рейтинг менеджеров по количеству проданных товаров",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = PurchaseRatingCountDTO.class)))
            }
    )
    @PostMapping("rating/count")
    public List<PurchaseRatingCountDTO> getCountRating(@RequestBody RequestParams params) {
        return service.getCountRating(params);
    }

    @Operation(
            summary = "Получить информацию по фильтру рейтинг магазинов по сумме проданных товаров",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = PurchaseRatingAmountShopDTO.class)))
            }
    )
    @PostMapping("shop-rating/amount")
    public List<PurchaseRatingAmountShopDTO> getAmountShopRating(@RequestBody RequestParams params) {
        return service.getAmountShopRating(params);
    }

    @Operation(
            summary = "Получить информацию по фильтру рейтинг магазинов по количеству проданных товаров",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = PurchaseRatingCountShopDTO.class)))
            }
    )
    @PostMapping("shop-rating/count")
    public List<PurchaseRatingCountShopDTO> getCountShopRating(@RequestBody RequestParams params) {
        return service.getCountShopRating(params);
    }

    @Operation(
            summary = "Получить информацию о всех годах",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = String.class)))
            }
    )
    @GetMapping("years")
    public List<String> getYears() {
        return service.getYears();
    }

}

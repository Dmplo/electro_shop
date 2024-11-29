package dev.plotnikov.page.entity.payload;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PurchasePayload(
        @NotNull(message = "Поле purchase.id не должно быть пустым")
        Long id,
        @NotNull(message = "Поле Сумма не должно быть пустым")
        Long amount,
        @NotNull(message = "Поле Количество не должно быть пустым")
        Long count,
        @NotNull(message = "Поле purchase.electro_id не должно быть пустым")
        Long electroId,
        @NotNull(message = "Поле purchase.employee_id не должно быть пустым")
        Long employeeId,
        @NotNull(message = "Поле Дата заказа не должно быть пустым")
        LocalDateTime purchaseDate,
        @NotNull(message = "Поле purchase.type_id не должно быть пустым")
        Long typeId,
        @NotNull(message = "Поле purchase.shop_id не должно быть пустым")
        Long shopId
) {
}





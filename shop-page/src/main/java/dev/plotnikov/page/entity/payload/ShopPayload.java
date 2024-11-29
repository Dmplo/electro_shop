package dev.plotnikov.page.entity.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ShopPayload(
        @NotNull(message = "Поле electro_shop.id не должно быть пустым")
        Long id,
        @NotBlank(message = "Поле Название магазина не должно быть пустым")
        @Size(max = 250, message = "Название магазина должно быть не более {max} символов")
        String name,
        @NotNull(message = "Поле Адрес магазина не должно быть пустым")
        @NotBlank(message = "Поле Адрес магазина не должно быть пустым")
        String address
) {
}

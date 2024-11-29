package dev.plotnikov.page.entity.payload;

import jakarta.validation.constraints.NotNull;

public record ElectroShopPayload(
        @NotNull(message = "Поле electro_shop.shop_id не должно быть пустым")
        Long shopId,
        @NotNull(message = "Поле electro_shop.electro_item_id не должно быть пустым")
        Long electroItemId,
        @NotNull(message = "Поле Количество товара в магазине не должно быть пустым")
        Integer count) {
}


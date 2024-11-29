package dev.plotnikov.page.entity.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ElectroItemPayload(
        @NotNull(message = "Поле electro_item.id не должно быть пустым")
        Long id,
        @NotBlank(message = "Поле Наименование товара не должно быть пустым")
        @Size(max = 150, message = "Наименование товара должно быть не более {max} символов")
        String name,
        @NotNull(message = "Поле electro_item.type_id не должно быть пустым")
        Long typeId,
        @NotNull(message = "Поле Цена не должно быть пустым")
        Long price,
        @NotNull(message = "Поле Количество не должно быть пустым")
        Integer count,
        @NotNull(message = "Поле Архив не должно быть пустым")
        Boolean archive,
        @NotNull(message = "Поле Описание не должно быть пустым")
        String text
) {
}

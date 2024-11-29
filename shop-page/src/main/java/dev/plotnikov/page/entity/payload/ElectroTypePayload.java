package dev.plotnikov.page.entity.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ElectroTypePayload(
        @NotNull(message = "Поле electro_type.id не должно быть пустым")
        Long id,
        @NotBlank(message = "Поле Тип товара не должно быть пустым")
        @Size(max = 150, message = "Тип товара должен быть не более {max} символов")
        String name
) {
}

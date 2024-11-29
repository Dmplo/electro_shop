package dev.plotnikov.page.entity.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PositionTypePayload(
        @NotNull(message = "Поле position_type.id не должно быть пустым")
        Long id,
        @NotBlank(message = "Поле Должность не должно быть пустым")
        @Size(max = 150, message = "Должность должна быть не более {max} символов")
        String name
) {
}

package dev.plotnikov.page.entity.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EmployeePayload(

        @NotNull(message = "Поле employee.id не должно быть пустым")
        Long id,

        @NotBlank(message = "Поле Фамилия не должно быть пустым")
        @Size(max = 100, message = "Фамилия должна быть не более {max} символов")
        String lastName,
        @NotBlank(message = "Поле Имя не должно быть пустым")
        @Size(max = 100, message = "Имя должно быть не более {max} символов")
        String firstName,
        @NotBlank(message = "Поле Отчество не должно быть пустым")
        @Size(max = 100, message = "Отчество должна быть не более {max} символов")
        String patronymic,
        LocalDate birthDate,
        @NotNull(message = "Поле employee.position_id не должно быть пустым")
        Long positionId,
        @NotNull(message = "Поле employee.shop_id не должно быть пустым")
        Long shopId,
        @NotNull(message = "Поле Пол не должно быть пустым")
        Boolean gender
) {

}
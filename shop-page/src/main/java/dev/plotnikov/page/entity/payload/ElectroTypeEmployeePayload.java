package dev.plotnikov.page.entity.payload;

import jakarta.validation.constraints.NotNull;

public record ElectroTypeEmployeePayload(
        @NotNull(message = "Поле electro_type_employee.employee_id не должно быть пустым")
        Long employeeId,
        @NotNull(message = "Поле electro_type_employee.electro_type_id не должно быть пустым")
        Long electroTypeId
) {
}


package dev.plotnikov.page.entity;

public record EmployeeDTO(
        Long id,
        String lastName,
        String firstName,
        String patronymic,
        String gender,
        Long positionId,
        String positionName,
        Long shopId,
        String shopName,
        String shopAddress) {
}

package dev.plotnikov.page.entity;

public record PurchaseAllDTO(
    Long purchaseId,
    String purchaseDate,
    Long amount,
    String purchaseTypeName,
    Long productId,
    String productName,
    Long productTypeId,
    String productTypeName,
    Long shopId,
    String shopName,
    String shopAddress,
    Long employeeId,
    String employeeName,
    String positionName){
}
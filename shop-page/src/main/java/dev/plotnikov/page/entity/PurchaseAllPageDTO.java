package dev.plotnikov.page.entity;

import java.util.List;


public record PurchaseAllPageDTO(
        List<PurchaseAllDTO> purchasesDTO,
        int currentPage,
        long totalPages
) {
}

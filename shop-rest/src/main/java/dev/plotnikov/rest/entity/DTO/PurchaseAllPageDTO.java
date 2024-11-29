package dev.plotnikov.rest.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseAllPageDTO {
    private List<PurchaseAllDTO> purchasesDTO;
    private int currentPage;
    private long totalPages;

}

package dev.plotnikov.page.controller;

import dev.plotnikov.page.entity.ProductAllPageDTO;
import dev.plotnikov.page.service.ProductsRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductsRestService purchasesService;

    @GetMapping("/products/pageable")
    public ResponseEntity<ProductAllPageDTO> getPageableList(
            @RequestParam(name = "type", required = false) Long typeId,
            @RequestParam(required = false) Integer page
    ) {
        return ResponseEntity.ok(purchasesService.getAll(typeId, page));
    }
}

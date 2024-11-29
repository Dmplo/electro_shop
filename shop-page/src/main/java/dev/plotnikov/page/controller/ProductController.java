package dev.plotnikov.page.controller;

import dev.plotnikov.page.entity.ProductAllPageDTO;
import dev.plotnikov.page.entity.ProductShopDTO;
import dev.plotnikov.page.service.ProductsRestService;
import dev.plotnikov.page.service.PurchasesRestService;
import dev.plotnikov.page.service.TypeRestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductsRestService productService;
    private final TypeRestService typeService;
    private final PurchasesRestService purchaseService;


    @GetMapping()
    public String getAll(
            Model model,
            @RequestParam(name = "type", required = false) Long typeId,
            @RequestParam(required = false) Integer page
            ) {
        ProductAllPageDTO result = productService.getAll(typeId, page);
        model.addAttribute("types", typeService.getAll());
        model.addAttribute("activeId", typeId);
        model.addAttribute("products", result.list());
        model.addAttribute("currentPage", result.currentPage());
        model.addAttribute("totalPages", result.totalPages());
        model.addAttribute("pageName", "products");
        model.addAttribute("isNotUpload", purchaseService.getCount() == 0);

        return "/products/list";
    }

    @GetMapping("{id:\\d+}")
    public String getById(@Valid @PathVariable("id") Long id, Model model) {
        ProductShopDTO product =  productService.getById(id)
                .orElseThrow(() -> new NoSuchElementException("Сотрудник с id = %d не найден".formatted(id)));
        model.addAttribute("product", product);
        return "/products/product";
    }

}


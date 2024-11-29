package dev.plotnikov.page.controller;

import dev.plotnikov.page.entity.payload.ShopPayload;
import dev.plotnikov.page.exception.CustomBadRequestException;
import dev.plotnikov.page.service.PurchasesRestService;
import dev.plotnikov.page.service.ShopRestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopRestService shopService;
    private final PurchasesRestService purchaseService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("shops", shopService.getAll());
        model.addAttribute("pageName", "shops");
        model.addAttribute("isNotUpload", purchaseService.getCount() == 0);
        return "/shops/list";
    }

    @GetMapping("{id:\\d+}")
    public String getById(@Valid @PathVariable("id") Long id, Model model) {
        ShopPayload shop = shopService.getById(id).orElseThrow(() -> new NoSuchElementException("Магазин с id = %d не найден".formatted(id)));
        model.addAttribute("isEdit", false);
        model.addAttribute("shop", shop);
        return "/shops/shop";
    }

    @GetMapping("edit/{id:\\d+}")
    public String getEditPage(@PathVariable("id") Long id, Model model) {
        ShopPayload shop = shopService.getById(id).orElseThrow(() -> new NoSuchElementException("Магазин с id = %d не найден".formatted(id)));
        model.addAttribute("isEdit", true);
        model.addAttribute("shop", shop);
        return "/shops/shop";
    }

    @PostMapping("edit/{id:\\d+}")
    public String update(@PathVariable("id") Long id, ShopPayload shop,
                                RedirectAttributes redirectAttributes) {
        try {
            this.shopService.update(shop);
            return "redirect:/shops/%d".formatted(shop.id());
        } catch (CustomBadRequestException exception) {
            redirectAttributes.addFlashAttribute("payload", shop);
            redirectAttributes.addFlashAttribute("errors", exception.getErrors());
            return "redirect:/shops/edit/%d".formatted(id);
        }
    }

}
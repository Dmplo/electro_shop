package dev.plotnikov.page.controller;

import dev.plotnikov.page.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRestService bookService;
    private final PurchasesRestService purchaseService;
    private final TypeRestService typeService;
    private final PositionRestService positionService;
    private final PurchaseTypRestService payService;

    @GetMapping
    public String getPage(Model model, @RequestParam(required = false) String section) {
        if (section == null || section.equalsIgnoreCase("positions")) {
            model.addAttribute("content", positionService.getAll());
        } else if (section.equalsIgnoreCase("pays")) {
            model.addAttribute("content", payService.getAll());
        } else {
            model.addAttribute("content", typeService.getAll());
        }

        model.addAttribute("sections", bookService.getCount());
        model.addAttribute("pageName", "books");
        model.addAttribute("isNotUpload", purchaseService.getCount() == 0);
        return "books/list";
    }

}
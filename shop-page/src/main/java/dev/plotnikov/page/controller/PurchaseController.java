package dev.plotnikov.page.controller;

import dev.plotnikov.page.controller.params.GetParams;
import dev.plotnikov.page.entity.PurchaseAllPageDTO;
import dev.plotnikov.page.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchasesRestService purchaseService;
    private final TypeRestService typeService;
    private final ShopRestService shopService;
    private final PositionRestService positionService;
    private final PurchaseTypRestService payService;

    @GetMapping
    public String getAll(Model model,
                         @RequestParam(required = false) List<Integer> year,
                         @RequestParam(required = false) List<Integer> type,
                         @RequestParam(required = false) List<Integer> shop,
                         @RequestParam(required = false) List<Integer> position,
                         @RequestParam(required = false) List<Integer> pay,
                         @RequestParam(required = false) String report,
                         @RequestParam(required = false) String view,
                         @RequestParam(required = false) Integer page
    ) {
        if (report == null || report.equals("all")) {
            PurchaseAllPageDTO result = purchaseService.getAll(new GetParams(year, type, shop, position, pay, report), page);
            model.addAttribute("purchases", result.purchasesDTO());
            model.addAttribute("currentPage", result.currentPage());
            model.addAttribute("totalPages", result.totalPages());

        } else {
            model.addAttribute("rating", purchaseService.getAllByRating(new GetParams(year, type, shop, position, pay, report), view));
            model.addAttribute("view", view);
        }
        model.addAttribute("years", purchaseService.getYears());
        model.addAttribute("types", typeService.getAll());
        model.addAttribute("shops", shopService.getAll());
        model.addAttribute("positions", positionService.getAll());
        model.addAttribute("pays", payService.getAll());
        model.addAttribute("pageName", "purchases");
        model.addAttribute("isNotUpload", purchaseService.getCount() == 0);

        return "/purchases/list";
    }

}


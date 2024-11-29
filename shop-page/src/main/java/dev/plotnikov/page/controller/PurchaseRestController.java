package dev.plotnikov.page.controller;

import dev.plotnikov.page.controller.params.GetParams;
import dev.plotnikov.page.entity.PurchaseAllPageDTO;
import dev.plotnikov.page.service.PurchasesRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PurchaseRestController {

    private final PurchasesRestService purchasesService;

    @GetMapping("/purchases/pageable")
    public ResponseEntity<PurchaseAllPageDTO> getPageableList(
            @RequestParam(required = false) List<Integer> year,
            @RequestParam(required = false) List<Integer> type,
            @RequestParam(required = false) List<Integer> shop,
            @RequestParam(required = false) List<Integer> position,
            @RequestParam(required = false) List<Integer> pay,
            @RequestParam(required = false) String report,
            @RequestParam(required = false) Integer page
    ) {
        return ResponseEntity.ok(purchasesService.getAll(new GetParams(year, type, shop, position, pay, report), page));
    }
}

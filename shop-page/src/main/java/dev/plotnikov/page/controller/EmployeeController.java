package dev.plotnikov.page.controller;

import dev.plotnikov.page.entity.EmployeeDTO;
import dev.plotnikov.page.entity.payload.EmployeePayload;
import dev.plotnikov.page.exception.CustomBadRequestException;
import dev.plotnikov.page.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRestService employeeService;
    private final ShopRestService shopService;
    private final PositionRestService positionService;
    private final PurchasesRestService purchaseService;

    @GetMapping
    public String getAll(Model model) {
        List<EmployeeDTO> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        model.addAttribute("pageName", "employees");
        model.addAttribute("isNotUpload", purchaseService.getCount() == 0);

        return "/employees/list";
    }

    @GetMapping("{id:\\d+}")
    public String getById(@Valid @PathVariable("id") Long id, Model model) {
        EmployeeDTO employee =  employeeService.getById(id)
                .orElseThrow(() -> new NoSuchElementException("Сотрудник с id = %d не найден".formatted(id)));
        model.addAttribute("employee", employee);
        model.addAttribute("isEdit", false);
        return "/employees/employee";
    }

    @GetMapping("edit/{id:\\d+}")
    public String getEditPage(@PathVariable("id") Long id, Model model) {
        EmployeeDTO employee =  employeeService.getById(id)
                .orElseThrow(() -> new NoSuchElementException("Сотрудник с id = %d не найден".formatted(id)));
        model.addAttribute("isEdit", true);
        model.addAttribute("shops", shopService.getAll());
        model.addAttribute("positions", positionService.getAll());
        model.addAttribute("employee", employee);
        return "/employees/employee";
    }

    @PostMapping("edit/{id:\\d+}")
    public String update(@PathVariable("id") Long id, EmployeePayload payload,
                                RedirectAttributes redirectAttributes) {
        try {
            this.employeeService.update(payload);
            return "redirect:/employees/%d".formatted(payload.id());
        } catch (CustomBadRequestException exception) {
            redirectAttributes.addFlashAttribute("payload", payload);
            redirectAttributes.addFlashAttribute("errors", exception.getErrors());
            return "redirect:/employees/edit/%d".formatted(id);
        }
    }

}

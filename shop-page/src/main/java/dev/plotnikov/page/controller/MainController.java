package dev.plotnikov.page.controller;

import dev.plotnikov.page.service.PurchasesRestService;
import dev.plotnikov.page.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final StorageService storageService;
    private final PurchasesRestService purchaseService;

    @GetMapping("/")
    public String listUploadedFiles(
            @RequestParam(required = false) String status,
            Model model) {

        model.addAttribute("isNotUpload", purchaseService.getCount() == 0);
        if (status == null ) {
            model.addAttribute("files", storageService.loadAll().map(
                            path -> MvcUriComponentsBuilder.fromMethodName(MainController.class,
                                    "serveFile", path.getFileName().toString()).build().toUri().toString())
                    .collect(Collectors.toList()));
        }

        return "index";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);

        if (file == null) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);

        StringBuilder sb = new StringBuilder();
        sb.append("Архив %s успешно загружен сервер!".formatted(file.getOriginalFilename()))
                .append(System.lineSeparator()).append("Запустите миграцию");

        redirectAttributes.addFlashAttribute("message", sb.toString());
        redirectAttributes.addFlashAttribute("fileName", file.getOriginalFilename());

        return "redirect:/";
    }

}

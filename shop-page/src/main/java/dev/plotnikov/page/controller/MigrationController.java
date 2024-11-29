package dev.plotnikov.page.controller;

import dev.plotnikov.page.exception.MigrationFailedException;
import dev.plotnikov.page.service.CreatorService;
import dev.plotnikov.page.service.FileUnzipService;
import dev.plotnikov.page.service.MigrationRestService;
import dev.plotnikov.page.storage.StorageProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
@RequestMapping("/migration")
public class MigrationController {

    private final FileUnzipService service;
    private final StorageProperties properties;
    private final CreatorService creatorService;
    private final MigrationRestService migrationService;

    @GetMapping
    public String startMigration(@RequestParam String name) throws MigrationFailedException {

        Path rootLocation = Paths.get(properties.getLocation());
        Path fileZip = rootLocation.resolve(Paths.get(name)).normalize().toAbsolutePath();
        Path destPath = rootLocation.resolve(Paths.get(name.replace(".zip", ""))).normalize().toAbsolutePath();

        if (!fileZip.getParent().equals(rootLocation.toAbsolutePath())) {
            throw new MigrationFailedException("Ошибка миграции");
        }

        try {
            service.getUnzip(fileZip, destPath);
        } catch (IOException e) {
            throw new MigrationFailedException("Ошибка миграции");
        }
        creatorService.createModels(destPath);

        return "redirect:/";
    }

    @GetMapping("random")
    public String randomMigration() {
        migrationService.randomMigration();
        return "redirect:/";
    }

}

package dev.plotnikov.rest.controller;

import dev.plotnikov.rest.entity.DTO.BookDTO;
import dev.plotnikov.rest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/books")
@Tag(name = "Book", description = "API для работы с каталогом")
public class BookController {

private final BookService service;

    @Operation(
            summary = "Получить информацию о данных каталога",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = BookDTO.class)))
            }
    )
    @GetMapping
    public List<BookDTO> getInfo() {
        return service.getCount();
    }

}

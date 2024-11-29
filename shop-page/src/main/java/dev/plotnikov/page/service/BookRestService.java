package dev.plotnikov.page.service;

import dev.plotnikov.page.entity.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookRestService {

    private final RestClient restClient;

    public List<BookDTO> getCount() {
        return this.restClient
                .get()
                .uri("/api/books")
                .retrieve()
                .body(new ParameterizedTypeReference<List<BookDTO>>() {
                });    }

}

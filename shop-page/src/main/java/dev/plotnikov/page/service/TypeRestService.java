package dev.plotnikov.page.service;

import dev.plotnikov.page.entity.payload.ElectroTypePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeRestService {

    private final RestClient restClient;

    public List<ElectroTypePayload> getAll() {
        return this.restClient
                .get()
                .uri("/api/types")
                .retrieve()
                .body(new ParameterizedTypeReference<List<ElectroTypePayload>>() {
                });
    }

}

package dev.plotnikov.page.service;

import dev.plotnikov.page.entity.payload.PurchaseTypePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseTypRestService {

    private final RestClient restClient;

    public List<PurchaseTypePayload> getAll() {
        return this.restClient
                .get()
                .uri("/api/pays")
                .retrieve()
                .body(new ParameterizedTypeReference<List<PurchaseTypePayload>>() {
                });
    }

}

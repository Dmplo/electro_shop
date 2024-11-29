package dev.plotnikov.page.service;

import dev.plotnikov.page.entity.payload.PositionTypePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PositionRestService {

    private final RestClient restClient;

    public List<PositionTypePayload> getAll() {
        return this.restClient
                .get()
                .uri("/api/positions")
                .retrieve()
                .body(new ParameterizedTypeReference<List<PositionTypePayload>>() {
                });
    }

}

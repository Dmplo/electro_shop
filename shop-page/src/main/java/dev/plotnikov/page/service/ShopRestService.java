package dev.plotnikov.page.service;

import dev.plotnikov.page.entity.payload.ShopPayload;
import dev.plotnikov.page.exception.CustomBadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopRestService {

    private final RestClient restClient;

    public List<ShopPayload> getAll() {
        return this.restClient
                .get()
                .uri("/api/shops")
                .retrieve()
                .body(new ParameterizedTypeReference<List<ShopPayload>>() {
                });
    }

    public Optional<ShopPayload> getById(Long shopId) {
        try {
            return Optional.ofNullable(this.restClient
                    .get()
                    .uri("/api/shops/{shopId}", shopId)
                    .retrieve()
                    .body(ShopPayload.class));
        } catch (HttpClientErrorException.NotFound exception) {
            return Optional.empty();
        }
    }

    public void update(ShopPayload shop) {
        try {
            this.restClient
                    .put()
                    .uri("/api/shops/update")
                    .body(shop)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.BadRequest exception) {
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new CustomBadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

}

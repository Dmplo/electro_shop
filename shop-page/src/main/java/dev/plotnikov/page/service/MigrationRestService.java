package dev.plotnikov.page.service;

import dev.plotnikov.page.entity.payload.ElectroTypePayload;
import dev.plotnikov.page.exception.CustomBadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MigrationRestService {

    private final RestClient restClient;

    public void randomMigration() {
        this.restClient
                .get()
                .uri("/api/migration/random")
                .retrieve()
                .toBodilessEntity();
    }

    public <T> ResponseEntity<Void> getMigration(List<T> list, String payload) {
        try {
            return this.restClient
                    .post()
                    .uri("/api/%s/migration".formatted(payload))
                    .body(list)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.BadRequest exception) {
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new CustomBadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }
}

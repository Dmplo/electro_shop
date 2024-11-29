package dev.plotnikov.page.service;

import dev.plotnikov.page.entity.EmployeeDTO;
import dev.plotnikov.page.entity.payload.EmployeePayload;
import dev.plotnikov.page.exception.CustomBadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class EmployeeRestService {

    private final RestClient restClient;

    public List<EmployeeDTO> getAll() {
        return this.restClient
                .get()
                .uri("/api/employees")
                .retrieve()
                .body(new ParameterizedTypeReference<List<EmployeeDTO>>() {
                });
    }

    public Optional<EmployeeDTO> getById(Long employeeId) {
        try {
            return Optional.ofNullable(this.restClient
                    .get()
                    .uri("/api/employees/{employeeId}", employeeId)
                    .retrieve()
                    .body(EmployeeDTO.class));
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchElementException(exception);
        }
    }


    public void update(EmployeePayload payload) {
        try {
            this.restClient
                    .put()
                    .uri("/api/employees/update")
                    .body(payload)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.BadRequest exception) {
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new CustomBadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }
}

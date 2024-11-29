package dev.plotnikov.page.service;

import dev.plotnikov.page.controller.params.GetParams;
import dev.plotnikov.page.entity.PurchaseAllPageDTO;
import dev.plotnikov.page.entity.PurchaseRatingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchasesRestService {

    private final RestClient restClient;

    public PurchaseAllPageDTO getAll(GetParams getParams, Integer page) {
        return this.restClient
                .post()
                .uri("/api/purchases?page={page}", page)
                .body(getParams)
                .retrieve()
                .body(PurchaseAllPageDTO.class);
    }

    public List<PurchaseRatingDTO> getAllByRating(GetParams getParams, String view) {
        if (view == null) {
            view = "amount";
        }
        return this.restClient
                .post()
                .uri("/api/purchases/%s/%s".formatted(getParams.getReport(), view))
                .body(getParams)
                .retrieve()
                .body(new ParameterizedTypeReference<List<PurchaseRatingDTO>>() {
                });
    }

    public Long getCount() {
        return this.restClient
                .get()
                .uri("/api/purchases/count")
                .retrieve()
                .body(Long.class);
    }

    public List<String> getYears() {
        return this.restClient
                .get()
                .uri("/api/purchases/years")
                .retrieve()
                .body(new ParameterizedTypeReference<List<String>>() {
                });
    }

}

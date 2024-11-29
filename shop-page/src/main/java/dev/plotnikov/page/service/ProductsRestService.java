package dev.plotnikov.page.service;

import dev.plotnikov.page.entity.ProductAllPageDTO;
import dev.plotnikov.page.entity.ProductShopDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsRestService {

    private final RestClient restClient;

    public ProductAllPageDTO getAll(Long typeId, Integer page) {
        String url = prepareUrl(typeId, page);
        return this.restClient
                .get()
                .uri(url)
                .retrieve()
                .body(ProductAllPageDTO.class);
    }

    private String prepareUrl(Long typeId, Integer page) {
        StringBuilder url = new StringBuilder();
        url.append("/api/products");
        boolean state = false;
        if (typeId != null) {
            url.append("?");
            state = true;
            url.append("type=%s".formatted(typeId));
        }
        if (page != null) {
            if (state) {
                url.append("&");
            } else {
                url.append("?");
            }
            url.append("page=%s".formatted(page));
        }
        return url.toString();
    }

    public Optional<ProductShopDTO> getById(Long productId) {
        try {
            return Optional.ofNullable(this.restClient
                    .get()
                    .uri("/api/products/{productId}", productId)
                    .retrieve()
                    .body(ProductShopDTO.class));
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchElementException(exception);
        }
    }






}

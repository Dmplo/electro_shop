package dev.plotnikov.rest.service;


import dev.plotnikov.rest.controller.params.RequestParams;
import dev.plotnikov.rest.entity.DTO.*;
import dev.plotnikov.rest.entity.Purchase;
import dev.plotnikov.rest.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository repository;
    private final ReportRatingAmountRepository reportRatingAmountRepository;
    private final ReportRatingAmountShopRepository reportRatingAmountShopRepository;
    private final ReportRatingCountRepository reportRatingCountRepository;
    private final ReportRatingCountShopRepository reportRatingCountShopRepository;
    private final ReportAllRepository reportAllRepository;

    public Purchase save(Purchase product) {
        return repository.save(product);
    }

    public List<String> getYears() {
        return repository.findDistinctYears();
    }

    public PurchaseAllPageDTO getAll(RequestParams params, Pageable pageable) {
            return reportAllRepository.findAllCustom(params, pageable);
    }

    public List<PurchaseRatingAmountDTO> getAmountRating(RequestParams params) {
        return reportRatingAmountRepository.findAmountRating(params);
    }

    public List<PurchaseRatingCountDTO> getCountRating(RequestParams params) {
        return reportRatingCountRepository.findCountRating(params);
    }

    public List<PurchaseRatingAmountShopDTO> getAmountShopRating(RequestParams params) {
        return reportRatingAmountShopRepository.findAmountShopRating(params);
    }

    public List<PurchaseRatingCountShopDTO> getCountShopRating(RequestParams params) {
        return reportRatingCountShopRepository.findCountShopRating(params);
    }

    public void saveAll(List<Purchase> purchases) {
        repository.saveAll(purchases);
    }

    public Long getCount() {
       return repository.findPurchaseCount();
    }
}

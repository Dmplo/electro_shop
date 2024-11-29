package dev.plotnikov.rest.service;


import dev.plotnikov.rest.entity.PurchaseType;
import dev.plotnikov.rest.repository.PurchaseTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PurchaseTypeService {

   private final PurchaseTypeRepository repository;

    public List<PurchaseType> getAll() {
        return repository.findAll();
    }

    public void saveAll(List<PurchaseType> purchaseTypes) {
        repository.saveAll(purchaseTypes);
    }
}

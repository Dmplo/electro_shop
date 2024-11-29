package dev.plotnikov.rest.service;

import dev.plotnikov.rest.entity.ElectroShop;
import dev.plotnikov.rest.repository.ElectroShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElectroShopService {

   private final ElectroShopRepository repository;

    public void saveAll(List<ElectroShop> shops) {
        repository.saveAll(shops);
    }
}

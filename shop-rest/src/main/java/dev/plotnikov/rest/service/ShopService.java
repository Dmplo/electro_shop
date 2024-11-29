package dev.plotnikov.rest.service;


import dev.plotnikov.rest.entity.Shop;
import dev.plotnikov.rest.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ShopService {

   private final ShopRepository repository;

    public List<Shop> getAll() {
        return repository.findAll();
    }

    public Shop getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Магазин с id = %d не найден".formatted(id)));
    }

    public void update(Shop shop) {
        Shop shopFromDb = getById(shop.getId());
        BeanUtils.copyProperties(shop, shopFromDb, "id");
        repository.save(shopFromDb);
    }

    public void saveAll(List<Shop> shops) {
        repository.saveAll(shops);
    }
}

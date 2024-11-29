package dev.plotnikov.rest.service;

import dev.plotnikov.rest.entity.DTO.ElectroItemPageDTO;
import dev.plotnikov.rest.entity.DTO.ElectroShopCount.ElectroItemDTO;
import dev.plotnikov.rest.entity.ElectroItem;
import dev.plotnikov.rest.repository.ElectroItemCustomRepository;
import dev.plotnikov.rest.repository.ElectroItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElectroItemService {

    private final ElectroItemRepository repository;
    private final ElectroItemCustomRepository customRepository;

    public ElectroItemPageDTO getAll(Long typeId, Pageable pageable) {
        Page<ElectroItem> page;
        if (typeId != null) {
            page = repository.findByType(typeId, pageable);
        } else {
            page = repository.findAllCustom(pageable);
        }
        return new ElectroItemPageDTO(page.getContent(), pageable.getPageNumber(), page.getTotalPages());

    }

    public ElectroItemDTO getById(Long id) {
        return customRepository.getById(id);
    }

    public void saveAll(List<ElectroItem> electroItems) {
        repository.saveAll(electroItems);
    }
}


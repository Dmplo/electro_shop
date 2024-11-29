package dev.plotnikov.rest.service;


import dev.plotnikov.rest.entity.PositionType;
import dev.plotnikov.rest.repository.PositionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PositionTypeService {

   private final PositionTypeRepository repository;

    public List<PositionType> getAll() {
        return repository.findAll();
    }

    public void saveAll(List<PositionType> positionTypes) {
        repository.saveAll(positionTypes);
    }
}

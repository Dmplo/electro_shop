package dev.plotnikov.rest.service;


import dev.plotnikov.rest.entity.ElectroType;
import dev.plotnikov.rest.repository.ElectroTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ElectroTypeService {

   private final ElectroTypeRepository repository;

    public List<ElectroType> getAll() {
        return repository.findAll();
    }

    public void saveAll(List<ElectroType> electroTypes) {
        repository.saveAll(electroTypes);
    }
}

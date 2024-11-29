package dev.plotnikov.rest.service;

import dev.plotnikov.rest.entity.ElectroTypeEmployee;
import dev.plotnikov.rest.repository.ElectroTypeEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElectroTypeEmployeeService {

   private final ElectroTypeEmployeeRepository repository;

    public void saveAll(List<ElectroTypeEmployee> electroTypeEmployees) {
        repository.saveAll(electroTypeEmployees);
    }
}

package dev.plotnikov.rest.service;

import dev.plotnikov.rest.entity.DTO.EmployeeDTO;
import dev.plotnikov.rest.entity.Employee;
import dev.plotnikov.rest.repository.EmployeeCustomRepository;
import dev.plotnikov.rest.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeCustomRepository customRepository;
    private final EmployeeRepository repository;

    public Employee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Сотрудник с id = %d не найден".formatted(id)));
    }

    public void update(Employee employee) {
        Employee employeeFromDb = getById(employee.getId());
        BeanUtils.copyProperties(employee, employeeFromDb, "id", "birthDate");
        repository.save(employeeFromDb);
    }

    public List<EmployeeDTO> getAllCustom() {
        return customRepository.findAll();
    }

    public EmployeeDTO getByIdCustom(Long id) {
        return customRepository.findById(id);
    }

    public void saveAll(List<Employee> employees) {
        repository.saveAll(employees);
    }
}

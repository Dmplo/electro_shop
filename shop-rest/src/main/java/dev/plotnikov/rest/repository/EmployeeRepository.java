package dev.plotnikov.rest.repository;


import dev.plotnikov.rest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where e.lastName = ?1 and e.firstName = ?2 and e.patronymic = ?3 and e.birthDate = ?4")
    public Employee findFullName(String lastName, String firstName, String patronymic, Date birthDate);

    @Query("select e from Employee e where e.positionId = ?1")
    public List<Employee> findByPosition(Long positionId);

}


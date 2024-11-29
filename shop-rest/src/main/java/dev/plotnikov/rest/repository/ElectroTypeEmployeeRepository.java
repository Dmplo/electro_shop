package dev.plotnikov.rest.repository;

import dev.plotnikov.rest.entity.ElectroTypeEmployee;
import dev.plotnikov.rest.entity.ElectroTypeEmployeePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectroTypeEmployeeRepository extends JpaRepository<ElectroTypeEmployee, ElectroTypeEmployeePK> {

}

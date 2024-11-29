package dev.plotnikov.rest.repository;

import dev.plotnikov.rest.entity.ElectroType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectroTypeRepository extends JpaRepository<ElectroType, Long> {

}

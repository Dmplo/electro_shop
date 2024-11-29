package dev.plotnikov.rest.repository;

import dev.plotnikov.rest.entity.PositionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionTypeRepository extends JpaRepository<PositionType, Long> {

}

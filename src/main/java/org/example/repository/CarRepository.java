package org.example.repository;
import org.example.domain.CarPolimorfico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarPolimorfico, Long> {
}

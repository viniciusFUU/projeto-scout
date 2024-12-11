package project.scout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.scout.model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer>{
    Position findByPositionDescription(String positionDescription);
}
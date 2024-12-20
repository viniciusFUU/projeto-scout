package project.scout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.scout.model.PlayerStats;

@Repository
public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Integer>{
    
}

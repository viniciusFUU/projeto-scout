package project.scout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.scout.model.MatchStats;

@Repository
public interface MatchStatsRepository extends JpaRepository<MatchStats, Integer>{
    
}

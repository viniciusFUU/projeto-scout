package project.scout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.scout.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer>{
    
}

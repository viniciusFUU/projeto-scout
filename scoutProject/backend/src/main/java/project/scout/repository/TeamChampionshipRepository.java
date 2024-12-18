package project.scout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.scout.model.TeamChampionship;

@Repository
public interface TeamChampionshipRepository extends JpaRepository<TeamChampionship, Integer>{
    
}

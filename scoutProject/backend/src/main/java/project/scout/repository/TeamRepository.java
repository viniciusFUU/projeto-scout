package project.scout.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.scout.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>{
    // Optional<Team> findTeamByName(String teamName);
    Team findByTeamId(int teamId);
}
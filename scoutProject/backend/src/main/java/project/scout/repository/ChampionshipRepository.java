package project.scout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.scout.model.Championship;

@Repository
public interface ChampionshipRepository extends JpaRepository<Championship, Integer>{

}
package project.scout.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.scout.model.Championship;
import project.scout.model.ChampionshipStats;
import project.scout.model.Statistic;

@Repository
public interface ChampionshipStatsRepository extends JpaRepository<ChampionshipStats, Integer>{
    List<Statistic> findByChampionshipId(Championship championshipId);;
}

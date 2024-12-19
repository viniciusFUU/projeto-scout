package project.scout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.scout.model.Statistic;

public interface StatisticRepository extends JpaRepository<Statistic, Integer>{
    Statistic findByStatisticDescription(String statisticDescription);
    Statistic findById(int id);
}

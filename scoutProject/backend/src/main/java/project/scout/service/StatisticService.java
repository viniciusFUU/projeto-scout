package project.scout.service;

import org.springframework.stereotype.Service;

import project.scout.model.Statistic;
import project.scout.repository.StatisticRepository;

@Service
public class StatisticService {
    private StatisticRepository statisticRepository;

    public StatisticService(StatisticRepository statisticRepository){
        this.statisticRepository = statisticRepository;
    }

    public Statistic getStatisticByDescription(String description){
        Statistic statistic = statisticRepository.findByStatisticDescription(description);

        return statistic;
    }
}

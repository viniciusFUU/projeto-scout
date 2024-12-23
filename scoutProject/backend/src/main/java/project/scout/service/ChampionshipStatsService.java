package project.scout.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import project.scout.model.Championship;
import project.scout.model.ChampionshipStats;
import project.scout.model.Statistic;
import project.scout.repository.ChampionshipRepository;
import project.scout.repository.ChampionshipStatsRepository;
import project.scout.repository.MatchStatsRepository;
import project.scout.repository.StatisticRepository;

@Service
public class ChampionshipStatsService {
    public final ChampionshipStatsRepository championshipStatsRepository;
    public final ChampionshipRepository championshipRepository;
    public final StatisticRepository statisticRepository;
    public final MatchStatsRepository matchStatsRepository;

    public ChampionshipStatsService (ChampionshipStatsRepository championshipStatsRepository, ChampionshipRepository championshipRepository, StatisticRepository statisticRepository, MatchStatsRepository matchStatsRepository){
        this.championshipStatsRepository = championshipStatsRepository;
        this.championshipRepository = championshipRepository;
        this.statisticRepository = statisticRepository;
        this.matchStatsRepository = matchStatsRepository;
    }

    public void insertDataIntoChampionshipStatsTable(int championshipId, int statisticId){
        Championship championship = championshipRepository.findByChampionshipId(championshipId);
        Statistic statistic = statisticRepository.findById(statisticId);

        ChampionshipStats championshipStats = new ChampionshipStats();
        championshipStats.setChampionshipId(championship);
        championshipStats.setStatisticId(statistic);

        championshipStatsRepository.save(championshipStats);
    }

    public List<ChampionshipStats> getAllChampionshipStats(){
        List<ChampionshipStats> allStatsList = championshipStatsRepository.findAll();

        return allStatsList;
    }

    public List<ChampionshipStats> getStatsByChampionshipName(int championshipId){
        List<ChampionshipStats> championshipStatsList = new ArrayList<>();

        for(ChampionshipStats championshipStats : getAllChampionshipStats()){
            if(championshipStats.getChampionshipId().getChampionshipId() == championshipId){
                championshipStatsList.add(championshipStats);
            }
        }

        return championshipStatsList;
    }
}

package project.scout.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import project.scout.model.MatchStats;
import project.scout.model.Player;
import project.scout.model.PlayerStats;
import project.scout.model.Statistic;
import project.scout.repository.MatchStatsRepository;
import project.scout.repository.PlayerRepository;
import project.scout.repository.PlayerStatsRepository;
import project.scout.repository.StatisticRepository;

@Service
public class PlayerStatsService {
    private final PlayerStatsRepository playerStatsRepository;
    private final PlayerRepository playerRepository;
    private final StatisticRepository statisticRepository;
    private final MatchStatsRepository matchStatsRepository;

    public PlayerStatsService(PlayerStatsRepository playerStatsRepository, PlayerRepository playerRepository, StatisticRepository statisticRepository, MatchStatsRepository matchStatsRepository){
        this.playerStatsRepository = playerStatsRepository;
        this.playerRepository = playerRepository;
        this.statisticRepository = statisticRepository;
        this.matchStatsRepository = matchStatsRepository;
    }

    public void insertPlayerStats(int playerId, int statisticId){
        Player player = playerRepository.findByPlayerId(playerId);
        Statistic statistic = statisticRepository.findById(statisticId);

        List<MatchStats> statsList = matchStatsRepository.findAll();

        try{
            for(MatchStats matchStats : statsList){
                if(matchStats.getTeamPlayerId().getPlayerId() == player){
                    PlayerStats playerStats = new PlayerStats();
                    playerStats.setPlayerId(player);
                    playerStats.setStatisticId(statistic);

                    playerStatsRepository.save(playerStats);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }    
    }

    public List<PlayerStats> getAllPlayerStats(){
        return playerStatsRepository.findAll();
    }

    public List<Statistic> getAllPlayersStatsByName(String playerName){
        List<Statistic> playerStatsList = new ArrayList<>();
        List<PlayerStats> listPlayerStats = playerStatsRepository.findAll();

        for (PlayerStats playerStats : listPlayerStats){
            if(playerStats.getPlayerId().getPlayerName().equals(playerName)){
                playerStatsList.add(playerStats.getStatisticId());
            }
        }

        return playerStatsList;
    }
}

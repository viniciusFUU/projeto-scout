package project.scout.service;

import org.springframework.stereotype.Service;

import project.scout.DTO.MatchStatsDTO;
import project.scout.model.Match;
import project.scout.model.MatchStats;
import project.scout.model.Statistic;
import project.scout.model.TeamChampionship;
import project.scout.model.TeamPlayer;
import project.scout.repository.MatchRepository;
import project.scout.repository.MatchStatsRepository;
import project.scout.repository.StatisticRepository;
import project.scout.repository.TeamChampionshipRepository;
import project.scout.repository.TeamPlayerRepository;

@Service
public class MatchStatsService {
    private final MatchStatsRepository matchStatsRepository;
    private final MatchRepository matchRepository;
    private final StatisticRepository statisticRepository;
    private final TeamPlayerRepository teamPlayerRepository;
    private final TeamChampionshipRepository teamChampionshipRepository;
    private final PlayerStatsService playerStatsService;
    private final TeamStatsService teamStatsService;

    public MatchStatsService(MatchStatsRepository matchStatsRepository, MatchRepository matchRepository, StatisticRepository statisticRepository, TeamPlayerRepository teamPlayerRepository, TeamChampionshipRepository teamChampionshipRepository, PlayerStatsService playerStatsService, TeamStatsService teamStatsService){
        this.matchStatsRepository = matchStatsRepository;
        this.matchRepository = matchRepository;
        this.statisticRepository = statisticRepository;
        this.teamPlayerRepository = teamPlayerRepository;
        this.teamChampionshipRepository = teamChampionshipRepository;
        this.playerStatsService = playerStatsService;
        this.teamStatsService = teamStatsService;

    }

    public String insertStats(MatchStatsDTO matchStatsDTO){
        Match match = matchRepository.findByMatchId(matchStatsDTO.getMatchId());
        Statistic statistic = statisticRepository.findById(matchStatsDTO.getStatisticId());
        TeamPlayer teamPlayer = teamPlayerRepository.findByTeamPlayerId(matchStatsDTO.getTeamPlayerId());
        TeamChampionship teamChampionship = teamChampionshipRepository.findByTeamChampionshipId(matchStatsDTO.getTeamChampionshipId());

        MatchStats matchStats = new MatchStats();
        matchStats.setMatchId(match);
        matchStats.setStatisticId(statistic);
        matchStats.setTeamPlayerId(teamPlayer);
        matchStats.setTeamChampionshipId(teamChampionship);

        matchStatsRepository.save(matchStats);

        playerStatsService.insertPlayerStats(teamPlayer.getPlayerId().getPlayerId(), statistic.getStatisticId());
        teamStatsService.insertTeamStats(teamPlayer.getTeamId().getTeamId(), statistic.getStatisticId());

        return matchStats.getStatisticId().getStatisticDescription()+" do jogador "+matchStats.getTeamPlayerId().getPlayerId().getPlayerName();
    }
}

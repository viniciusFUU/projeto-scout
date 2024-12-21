package project.scout.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import project.scout.model.Statistic;
import project.scout.model.Team;
import project.scout.model.TeamStats;
import project.scout.repository.StatisticRepository;
import project.scout.repository.TeamRepository;
import project.scout.repository.TeamStatsRepository;

@Service
public class TeamStatsService {
    private final TeamStatsRepository teamStatsRepository;
    private final TeamRepository teamRepository;
    private final StatisticRepository statisticRepository;

    public TeamStatsService(TeamStatsRepository teamStatsRepository, TeamRepository teamRepository, StatisticRepository statisticRepository){
        this.teamStatsRepository = teamStatsRepository;
        this.teamRepository = teamRepository;
        this.statisticRepository = statisticRepository;
    }

    public void insertTeamStats(int teamId, int statsId){
        Team team = teamRepository.findByTeamId(teamId);
        Statistic statistic = statisticRepository.findById(statsId);

        TeamStats teamStats = new TeamStats();
        teamStats.setTeamId(team);
        teamStats.setStatisticId(statistic);

        teamStatsRepository.save(teamStats);
    }

    public List<TeamStats> getAllTeamStats(){
        return teamStatsRepository.findAll();
    }

    public List<TeamStats> getAllTeamStatsByTeamName(String name){
        List<TeamStats> teamsStatsList = new ArrayList<>();
        List<TeamStats> allTeamsStats = getAllTeamStats();

        for(TeamStats teamStats : allTeamsStats){
            if(teamStats.getTeamId().getTeamName().equals(name)){
                teamsStatsList.add(teamStats);
            }
        }

        return teamsStatsList;
    }
}

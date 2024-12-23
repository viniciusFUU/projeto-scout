package project.scout.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import project.scout.model.Championship;
import project.scout.model.Team;
import project.scout.model.TeamChampionship;
import project.scout.repository.ChampionshipRepository;
import project.scout.repository.TeamChampionshipRepository;
import project.scout.repository.TeamRepository;

@Service
public class TeamChampionshipService {
    private final TeamChampionshipRepository teamChampionshipRepository;
    private final TeamRepository teamRepository;
    private final ChampionshipRepository championshipRepository;

    public TeamChampionshipService(TeamChampionshipRepository teamChampionshipRepository, TeamRepository teamRepository, ChampionshipRepository championshipRepository){
        this.teamChampionshipRepository = teamChampionshipRepository;
        this.teamRepository = teamRepository;
        this.championshipRepository = championshipRepository;
    }

    public String createTeamChampionship(int teamId, int championshipId){
        Team team = teamRepository.findByTeamId(teamId);
        Championship championship = championshipRepository.findByChampionshipId(championshipId);

        TeamChampionship teamChampionship = new TeamChampionship();
        teamChampionship.setTeamId(team);
        teamChampionship.setChampionshipId(championship);

        teamChampionshipRepository.save(teamChampionship);

        return teamChampionship.getTeamId().getTeamName()+" faz parte do "+teamChampionship.getChampionshipId().getChampionshipName();
    }

    public List<TeamChampionship> getAllTeamChampionship(){
        List<TeamChampionship> listTeamChampionship = new ArrayList<>();
        try {
            for(TeamChampionship teamChampionship : teamChampionshipRepository.findAll()){
                listTeamChampionship.add(teamChampionship);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listTeamChampionship;
    }

    public String deleteTeamChampionship(String teamName, String championshipName){
        String relationChampionshipDeleted = "";
        String relationTeamDeleted = "";

        try {
            Team team = teamRepository.findByTeamName(teamName);
            Championship championship = championshipRepository.findByChampionshipName(championshipName);

            for(TeamChampionship teamChampionship : teamChampionshipRepository.findAll()){
                if(teamChampionship.getTeamId() == team && teamChampionship.getChampionshipId() == championship){
                    relationChampionshipDeleted = teamChampionship.getChampionshipId().getChampionshipName();
                    relationTeamDeleted = teamChampionship.getTeamId().getTeamName();
                    teamChampionshipRepository.delete(teamChampionship);
                }
            }

            return "Relacionamento entre o time "+relationTeamDeleted+" e o campeonato "+relationChampionshipDeleted+" foi excluído.";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Não relação entre os times";
    }

    public List<Team> getAllTeamsOfChampionship(int championshipId){
        List<Team> teamsList = new ArrayList<>();

        for(TeamChampionship teamChampionship : getAllTeamChampionship()){
            if(teamChampionship.getChampionshipId().getChampionshipId() == championshipId){
                teamsList.add(teamChampionship.getTeamId());
            }
        }

        return teamsList;
    }
}

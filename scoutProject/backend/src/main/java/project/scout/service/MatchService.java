package project.scout.service;

import java.util.List;

import org.springframework.stereotype.Service;

import project.scout.model.Championship;
import project.scout.model.Match;
import project.scout.model.Team;
import project.scout.model.TeamChampionship;
import project.scout.repository.ChampionshipRepository;
import project.scout.repository.MatchRepository;
import project.scout.repository.TeamChampionshipRepository;
import project.scout.repository.TeamRepository;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final TeamChampionshipRepository teamChampionshipRepository;
    private final ChampionshipRepository championshipRepository;
    private final TeamRepository teamRepository;

    public MatchService(MatchRepository matchRepository, TeamChampionshipRepository teamChampionshipRepository, ChampionshipRepository championshipRepository, TeamRepository teamRepository){
        this.matchRepository = matchRepository;
        this.teamChampionshipRepository = teamChampionshipRepository;
        this.championshipRepository = championshipRepository;
        this.teamRepository = teamRepository;
    }

    public boolean isExistTeamWithChampionship(String championshipName, String teamHomeParam, String teamVisitParam) {
        boolean homeExists = false;
        boolean visitExists = false;
    
        Championship championship = championshipRepository.findByChampionshipName(championshipName);
        Team teamHome = teamRepository.findByTeamName(teamHomeParam);
        Team teamVisit = teamRepository.findByTeamName(teamVisitParam);
        
        for(TeamChampionship teamChampionship : teamChampionshipRepository.findAll()){
            if(teamChampionship.getChampionshipId() == championship && teamChampionship.getTeamId() == teamHome){
                homeExists = true;
            }

            if(teamChampionship.getChampionshipId() == championship && teamChampionship.getTeamId() == teamVisit){
                visitExists = true;
            }
        }
        
        if(homeExists && visitExists){
            return true;
        }

        return false;
    }

    public String createMatch(String championshipName, String teamHomeParam, String teamVisitParam){
        boolean isExist = isExistTeamWithChampionship(championshipName, teamHomeParam, teamVisitParam);

        Championship championship = championshipRepository.findByChampionshipName(championshipName);
        Team teamHome = teamRepository.findByTeamName(teamHomeParam);
        Team teamVisit = teamRepository.findByTeamName(teamVisitParam);

        Match match = new Match();
        match.setChampionshipId(championship);
        match.setTeamHome(teamHome);
        match.setTeamVisit(teamVisit);
        
        if(isExist == true){
            matchRepository.save(match);
            return "Partida entre "+match.getTeamHome().getTeamName()+" e "+match.getTeamVisit().getTeamName()+" criada com sucesso";
        }

        return "Os times "+match.getTeamHome().getTeamName()+" e "+match.getTeamVisit().getTeamName()+" não fazem parte do mesmo campeonato";
    }

    public List<Match> getAllMatchs(){
        return matchRepository.findAll();
    }

    public String deleteMatch(String championshipParam, String teamHomeParam, String teamVisitParam){
        boolean exist = isExistTeamWithChampionship(championshipParam, teamHomeParam, teamVisitParam);
        List<Match> matchsList = matchRepository.findAll();

        String teamHomeName = "";
        String teamVisitName = "";

        Championship championship = championshipRepository.findByChampionshipName(championshipParam);
        Team teamHome = teamRepository.findByTeamName(teamHomeParam);
        Team teamVisit = teamRepository.findByTeamName(teamVisitParam);

        try {
            if(exist){
                for(Match match : matchsList){
                    if(match.getChampionshipId() == championship && match.getTeamHome() == teamHome && match.getTeamVisit() == teamVisit){
                        teamHomeName = match.getTeamHome().getTeamName();
                        teamVisitName = match.getTeamVisit().getTeamName();
                        matchRepository.delete(match);
    
                        return "Partida entre "+teamHomeName+" e "+teamVisitName+" foi excluído com sucesso!";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Sem partida entre os times";
    
    }
}

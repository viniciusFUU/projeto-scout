package project.scout.service;

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

    public boolean IsExistTeamWithChampionship(String championshipName, String teamHomeParam, String teamVisitParam) {
        boolean result = false;
    
        Championship championship = championshipRepository.findByChampionshipName(championshipName);
        Team teamHome = teamRepository.findByTeamName(teamHomeParam);
        Team teamVisit = teamRepository.findByTeamName(teamVisitParam);
    
        for (TeamChampionship teamChampionship : teamChampionshipRepository.findAll()) {
            System.out.println(teamChampionship.getChampionshipId().getChampionshipName()+ teamChampionship.getTeamId().getTeamName());
            if (teamChampionship.getChampionshipId().equals(championship) && 
                teamChampionship.getTeamId().equals(teamHome) && 
                teamChampionship.getTeamId().equals(teamVisit)) {
                result = true;
            }
        }
    
        return result;
    }

    public String createMatch(String championshipName, String teamHomeParam, String teamVisitParam){
        boolean isExist = IsExistTeamWithChampionship(championshipName, teamHomeParam, teamVisitParam);

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
}

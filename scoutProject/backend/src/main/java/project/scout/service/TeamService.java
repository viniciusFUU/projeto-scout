package project.scout.service;

import java.util.List;

import org.springframework.stereotype.Service;

import project.scout.model.Team;
import project.scout.repository.TeamRepository;

@Service
public class TeamService {
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public Team createPlayer(Team team){
        Team playerCreated = teamRepository.save(team); 
    
        return playerCreated; 
    }

    public List<Team> getAllTeam(){
        List<Team> teamList = teamRepository.findAll();

        return teamList;
    }

    public String getTeamByName(String name){
        String teamFound = "";
        try {
            for(Team team : teamRepository.findAll()){
                if(team.getTeamName().equals(name)){
                    teamFound = team.getTeamName();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(teamFound != ""){
            return teamFound;
        }

        return "Não existe o time "+name;
    }

    public String updateTeamByName(String name, String valueToUpdate, String newValue){
        String oldValues = "";

        try {
            for(Team team : teamRepository.findAll()){
                if(team.getTeamName().equals(name)){
                    switch (valueToUpdate) {
                        case "teamName":
                        oldValues = team.getTeamName();
                            team.setTeamName(newValue);
                            break;
                        case "qtdPlayers":
                            oldValues = String.format(oldValues, team.getQtdPlayers());
                            Integer newValueParam = Integer.parseInt(newValue); 
                            team.setQtdPlayers(newValueParam);
                        case "firstColor":
                            oldValues = team.getFirstColor();
                            team.setFirstColor(newValue);
                        case "secondColor":
                            oldValues = team.getSecondColor();
                            team.setSecondColor(oldValues);
                        default:
                            throw new IllegalArgumentException("Campo inválido: " + valueToUpdate);
                        }

                        teamRepository.save(team);
                        return "Jogador "+oldValues+" teve seu "+valueToUpdate+" alterado para "+newValue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Não houve alterações";
    }

    public String deleteByName(String name){
        String teamDeleted = "";

        try {
            for(Team team : teamRepository.findAll()){
                if(team.getTeamName().equals(name)){
                    teamDeleted = team.getTeamName();
                    teamRepository.delete(team);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if(!teamDeleted.equals("")){
            return name+" excluído com sucesso";
        }
        
        return "Nenhum time encontrado com o nome "+name;
    }
}

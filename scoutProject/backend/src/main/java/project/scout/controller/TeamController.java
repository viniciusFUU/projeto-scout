package project.scout.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.scout.DTO.TeamDTO;
import project.scout.DTO.TopScoresDTO;
import project.scout.model.Team;
import project.scout.service.TeamService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/scout/team")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService){
        this.teamService = teamService;
    }

    @PostMapping
    public Team postMethodName(@RequestBody Team team) {        
        return teamService.createPlayer(team);
    }
    
    @GetMapping
    public List<Team> getAllTeam(){
        return teamService.getAllTeam();
    }

    @GetMapping("/name")
    public String getTeamByName(@RequestBody String name) {
        return teamService.getTeamByName(name);
    }   

    @GetMapping("/top-scores/{teamName}")
    public List<TopScoresDTO> getTopScores(@PathVariable String teamName) {
        return teamService.getTopScores(teamName);
    }

    @PutMapping
    public String updateTeamByName(@RequestBody TeamDTO request) {        
        return teamService.updateTeamByName(request.getTeamName(), request.getFieldToUpdate(), request.getNewValue());
    }

    @DeleteMapping("/name")
    public String deleteTeamByName(@RequestParam String param){
        return teamService.deleteByName(param);
    }
}

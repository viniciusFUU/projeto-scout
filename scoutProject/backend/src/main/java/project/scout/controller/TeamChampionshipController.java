package project.scout.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.scout.DTO.TeamChampionshipDTO;
import project.scout.model.Championship;
import project.scout.model.Team;
import project.scout.model.TeamChampionship;
import project.scout.service.TeamChampionshipService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/scout/team-championship")
@RestController
public class TeamChampionshipController {
    private final TeamChampionshipService teamChampionshipService;

    public TeamChampionshipController(TeamChampionshipService teamChampionshipService){
        this.teamChampionshipService = teamChampionshipService;
    }

    @PostMapping
    public String createTeamChampionship(@RequestBody TeamChampionshipDTO teamChampionshipDTO) {        
        return teamChampionshipService.createTeamChampionship(teamChampionshipDTO.getTeamId(), teamChampionshipDTO.getChampionshipId());
    }

    @GetMapping
    public List<TeamChampionship> getAllTeamChampionship() {
        return teamChampionshipService.getAllTeamChampionship();
    }

    @GetMapping("/championship/{championshipId}")
    public List<Team> getTeamsOfChampionship(@PathVariable int championshipId) {
        return teamChampionshipService.getAllTeamsOfChampionship(championshipId);
    }

    @GetMapping("/team/{teamId}")
    public List<Championship> getChampionshipOfTeam(@PathVariable int teamId) {
        return teamChampionshipService.getAllChampionshipOfTeam(teamId);
    }
    
    
    @DeleteMapping
    public String deleteTeamChampionship(@RequestBody TeamChampionshipDTO championshipDTO){
        return teamChampionshipService.deleteTeamChampionship(championshipDTO.getTeamName(), championshipDTO.getChampionshipName());
    }
}

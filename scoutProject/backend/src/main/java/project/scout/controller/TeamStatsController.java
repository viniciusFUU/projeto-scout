package project.scout.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.scout.model.TeamStats;
import project.scout.service.TeamStatsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/scout/team-stats")
@RestController
public class TeamStatsController {
    private final TeamStatsService teamStatsService;

    public TeamStatsController(TeamStatsService teamStatsService){
        this.teamStatsService = teamStatsService;
    }

    @GetMapping
    public List<TeamStats> getAllTeamStats() {
        return teamStatsService.getAllTeamStats();
    }

    @GetMapping("/{teamName}")
    public List<TeamStats> getStatsByTeamName(@PathVariable String teamName) {
        return teamStatsService.getAllTeamStatsByTeamName(teamName);
    }
    
    
}

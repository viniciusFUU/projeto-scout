package project.scout.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.scout.model.PlayerStats;
import project.scout.model.Statistic;
import project.scout.service.PlayerStatsService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("scout/player-stats")
public class PlayerStatsController {
    private final PlayerStatsService playerStatsService;

    public PlayerStatsController(PlayerStatsService playerStatsService){
        this.playerStatsService = playerStatsService;
    }   

    @GetMapping
    public List<PlayerStats> getAllPlayerStats() {
        return playerStatsService.getAllPlayerStats();
    }

    @GetMapping("/{name}")
    public List<Statistic> getStatisticsByP(@PathVariable String name) {
        return playerStatsService.getAllPlayersStatsByName(name);
    }
    
    
}

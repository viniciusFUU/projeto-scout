package project.scout.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.scout.model.ChampionshipStats;
import project.scout.service.ChampionshipStatsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/scout/championship-stats")
@RestController
public class ChampionshipStatsController {
    public final ChampionshipStatsService championshipStatsService;

    public ChampionshipStatsController(ChampionshipStatsService championshipStatsService){
        this.championshipStatsService = championshipStatsService;
    }

    @GetMapping
    public List<ChampionshipStats> getAllChampionshipStats() {
        return championshipStatsService.getAllChampionshipStats();
    }

    @GetMapping("/{id}")
    public List<ChampionshipStats> getStatsByChampionshipId(@PathVariable int id) {
        return championshipStatsService.getStatsByChampionshipName(id);
    }
    
    
}

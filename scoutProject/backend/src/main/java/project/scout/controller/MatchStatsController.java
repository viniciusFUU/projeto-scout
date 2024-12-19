package project.scout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.scout.DTO.MatchStatsDTO;
import project.scout.service.MatchStatsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("scout/match-stats")
@RestController
public class MatchStatsController {
    private final MatchStatsService matchStatsService;

    public MatchStatsController(MatchStatsService matchStatsService){
        this.matchStatsService = matchStatsService;
    }

    @PostMapping
    public String insertMatchStats(@RequestBody MatchStatsDTO matchStatsDTO) {
        return matchStatsService.insertStats(matchStatsDTO);
    }
    
}

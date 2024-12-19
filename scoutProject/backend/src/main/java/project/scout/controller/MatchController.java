package project.scout.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.scout.DTO.MatchDTO;
import project.scout.model.Match;
import project.scout.service.MatchService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RestController
@RequestMapping("scout/match")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService){
        this.matchService = matchService;
    }

    @PostMapping
    public String createMatch(@RequestBody MatchDTO matchDTO) {        
        return matchService.createMatch(matchDTO.getChampionshipName(), matchDTO.getTeamHomeName(), matchDTO.getTeamVisitName());
    }

    @GetMapping
    public List<Match> getAllMatchs() {
        return matchService.getAllMatchs();
    }

    @DeleteMapping
    public String deleteMatch(@RequestBody MatchDTO matchDTO){
        return matchService.deleteMatch(matchDTO.getChampionshipName(), matchDTO.getTeamHomeName(), matchDTO.getTeamVisitName());
    }
}

package project.scout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.scout.DTO.MatchDTO;
import project.scout.service.MatchService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    
}

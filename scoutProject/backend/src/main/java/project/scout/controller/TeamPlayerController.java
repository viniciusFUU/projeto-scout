package project.scout.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.scout.DTO.TeamPlayerDTO;
import project.scout.model.TeamPlayer;
import project.scout.service.TeamPlayerService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/scout/team-player")
public class TeamPlayerController {
    private final TeamPlayerService teamPlayerService;

    public TeamPlayerController(TeamPlayerService teamPlayerService){
        this.teamPlayerService = teamPlayerService;
    }

    @PostMapping
    public String createTeamPlayer(@RequestBody TeamPlayerDTO teamPlayerDTO){
        return teamPlayerService.createTeamPlayer(teamPlayerDTO.getTeamId(), teamPlayerDTO.getPlayerId());
    }

    @GetMapping
    public List<TeamPlayer> getAllTeamPlayers(){
        return teamPlayerService.getAllTeamPlayer();
    }

    @PutMapping
    public String updateTeamOfPLayer(@RequestBody TeamPlayerDTO teamPlayerDTO) {        
        return teamPlayerService.updateTeamOfPlayer(teamPlayerDTO.getPlayerId(), teamPlayerDTO.getTeamId());
    }
}


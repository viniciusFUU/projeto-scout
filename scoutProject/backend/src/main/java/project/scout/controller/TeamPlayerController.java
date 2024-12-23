package project.scout.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.scout.DTO.TeamPlayerDTO;
import project.scout.model.Player;
import project.scout.model.TeamPlayer;
import project.scout.service.TeamPlayerService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/player/{teamId}")
    public List<Player> getAllTeamsPlayer(@PathVariable int teamId) {
        return teamPlayerService.getAllTeamsPlayer(teamId);
    }
    

    @PutMapping("/update-team")
    public String updateTeamOfPLayer(@RequestBody TeamPlayerDTO teamPlayerDTO) {        
        return teamPlayerService.updateTeamOfPlayerByName(teamPlayerDTO.getPlayerName(), teamPlayerDTO.getTeamId());
    }

    @PutMapping("/update-position")
    public String updatePositionOfPLayer(@RequestBody TeamPlayerDTO teamPlayerDTO) {        
        return teamPlayerService.updatePositionsPlayerByName(teamPlayerDTO.getPlayerName(), teamPlayerDTO.getPositionId());
    }

    @DeleteMapping("/{id}")
    public String deleteTeamPlayerById(@PathVariable int id){
        return teamPlayerService.deleteTeamPlayerById(id);
    }
}


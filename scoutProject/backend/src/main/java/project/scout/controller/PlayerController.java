package project.scout.controller;

import org.springframework.web.bind.annotation.RestController;

import project.scout.DTO.PlayerDTO;
import project.scout.DTO.TopScoresDTO;
import project.scout.model.Player;
import project.scout.service.PlayerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/scout/player")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }
    
    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @GetMapping
    public List<Player> getAllPlayer() {
        return playerService.getAllPlayers();
    }
    
    
    @GetMapping("/name")
    public String getPlayerByName(@RequestParam String param) {
        return playerService.getPlayerByName(param);
    }

    @GetMapping("/top-scores")
    public List<TopScoresDTO> getTopScores() {
        return playerService.getTopScores();
    }
    

    @PutMapping
    public String updatePlayerByName(@RequestBody PlayerDTO request) {
        return playerService.updatePlayerByName(request.getName(), request.getFieldToUpdate(), request.getNewValue());
    }
    
    @DeleteMapping("/name")
    public String deletePlayerByName(@RequestParam String param){
        return playerService.deletePlayerByName(param);
    }
}

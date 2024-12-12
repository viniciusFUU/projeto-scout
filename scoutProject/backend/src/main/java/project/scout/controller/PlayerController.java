package project.scout.controller;

import org.springframework.web.bind.annotation.RestController;

import project.scout.model.Player;
import project.scout.service.PlayerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/scout/player")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayer() {
        return playerService.getAllPlayers();
    }
    
    
    @GetMapping("/name")
    public String getMethodName(@RequestParam String param) {
        return playerService.getPlayerByName(param);
    }

    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }
    
    
}

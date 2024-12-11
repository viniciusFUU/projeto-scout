package project.scout.service;

import org.springframework.stereotype.Service;

import project.scout.model.Player;
import project.scout.repository.PlayerRepository;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public Player createPlayer(Player player){
        Player playerCreated = playerRepository.save(player);

        return playerCreated;
    }
}

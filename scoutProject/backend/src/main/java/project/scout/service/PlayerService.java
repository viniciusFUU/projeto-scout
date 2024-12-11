package project.scout.service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Player> getAllPlayers(){
        List<Player> listPlayers = new ArrayList<>();

        try {
            for(Player player : playerRepository.findAll()){
                listPlayers.add(player);
            }
            
            return listPlayers;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listPlayers;
    }
}

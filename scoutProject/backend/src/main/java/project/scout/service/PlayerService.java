package project.scout.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import project.scout.DTO.TopAssistentsDTO;
import project.scout.DTO.TopPassersDTO;
import project.scout.DTO.TopScoresDTO;
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

    public String getPlayerByName(String name){
        String playerName = "";

        try {
            for(Player player : playerRepository.findAll()){
                if(player.getPlayerName().equals(name)){
                    playerName = player.getPlayerName();
                    break;
                }
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(playerName.equals("")){
            return "Não há jogadores com o nome "+name;
        }

        return playerName;
    }

    public List<TopScoresDTO> getTopScores(){
        List<TopScoresDTO> topScores = new ArrayList<>();
        
        List<Object[]> results = playerRepository.getTopStats(1);

        if(results != null){
            try {
                for(Object[] row : results){
                    TopScoresDTO data = new TopScoresDTO();
                    String playerNameV = (String) row[0];
                    data.setPlayerName(playerNameV);
                    String teamName = (String) row[1];
                    data.setTeamName(teamName);
                    Integer goals = ((Number) row[2]).intValue();
                    data.setScores(goals);

                    topScores.add(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return topScores;
    }

    public List<TopAssistentsDTO> getTopAssistents(){
        List<TopAssistentsDTO> topScores = new ArrayList<>();
        
        List<Object[]> results = playerRepository.getTopStats(2);

        if(results != null){
            try {
                for(Object[] row : results){
                    TopAssistentsDTO data = new TopAssistentsDTO();
                    String playerNameV = (String) row[0];
                    data.setPlayerName(playerNameV);

                    String teamName = (String) row[1];
                    data.setTeamName(teamName);
                    
                    Integer goals = ((Number) row[2]).intValue();
                    data.setAssistent(goals);

                    topScores.add(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return topScores;
    }

    public List<TopPassersDTO> getTopPassers(){
        List<TopPassersDTO> topPassers = new ArrayList<>();
        
        List<Object[]> results = playerRepository.getTopStats(3);

        if(results != null){
            try {
                for(Object[] row : results){
                    TopPassersDTO data = new TopPassersDTO();
                    String playerNameV = (String) row[0];
                    data.setPlayerName(playerNameV);

                    String teamName = (String) row[1];
                    data.setTeamName(teamName);

                    Integer pass = ((Number) row[2]).intValue();
                    data.setPasses(pass);

                    topPassers.add(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return topPassers;
    }

    public String updatePlayerByName(String name, String valueToAlter, String newValue) {
        try {
            for (Player player : playerRepository.findAll()) {
                if (player.getPlayerName().equals(name)) {
                    switch (valueToAlter) {
                        case "playerName":
                            player.setPlayerName(newValue);
                            break;
                        case "playerWeight":
                            player.setPlayerWeigth(Double.parseDouble(newValue));
                            break;
                        case "playerHeight":
                            player.setPlayerHeigth(Double.parseDouble(newValue));
                            break;
                        case "playerNumber":
                            player.setPlayerNumber(Double.parseDouble(newValue));
                            break;
                        default:
                            throw new IllegalArgumentException("Campo inválido: " + valueToAlter);
                    }
                    playerRepository.save(player);
                    return valueToAlter+" de jogador "+name+" alterado com sucesso";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return "Jogador não foi encontrado";
    }

    public String deletePlayerByName(String name){
        String playerName = "";

        try {
            for(Player player : playerRepository.findAll()){
                if(player.getPlayerName().equals(name)){
                    playerName = player.getPlayerName();
                    playerRepository.delete(player);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(playerName.equals("")){
            return "Nenhum jogador excluído";
        }

        return playerName+" excluído com sucesso.";
    }
}

package project.scout.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import project.scout.model.Player;
import project.scout.model.Position;
import project.scout.model.Team;
import project.scout.model.TeamPlayer;
import project.scout.repository.PlayerRepository;
import project.scout.repository.PositionRepository;
import project.scout.repository.TeamPlayerRepository;
import project.scout.repository.TeamRepository;

@Service
public class TeamPlayerService {
    private final TeamPlayerRepository teamPlayerRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final PositionRepository positionRepository;

    public TeamPlayerService(TeamPlayerRepository teamPlayerRepository, TeamRepository teamRepository, PlayerRepository playerRepository, PositionRepository positionRepository){
        this.teamPlayerRepository = teamPlayerRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.positionRepository = positionRepository;
    }

    public String createTeamPlayer(int teamParam, int playerParam){
        Team team = teamRepository.findById(teamParam).orElseThrow(() -> new EntityNotFoundException("Team not found"));
        Player player = playerRepository.findById(playerParam).orElseThrow(() -> new EntityNotFoundException("Player not found"));

        TeamPlayer teamPlayer = new TeamPlayer();
        teamPlayer.setTeamId(team);
        teamPlayer.setPlayerId(player);
        teamPlayerRepository.save(teamPlayer);

        return player.getPlayerName() + " faz parte do time " + team.getTeamName();
    }

    public List<TeamPlayer> getAllTeamPlayer(){
        List<TeamPlayer> listaTeamPlayers = new ArrayList<>();
        
        for (TeamPlayer teamPlayer : teamPlayerRepository.findAll()){
            listaTeamPlayers.add(teamPlayer);
        }

        return listaTeamPlayers;
    }

    public List<Player> getAllTeamsPlayer(int teamId){
        List<Player> playersList = new ArrayList<>();
        
        for(TeamPlayer teamPlayer : getAllTeamPlayer()){
            if(teamPlayer.getTeamId().getTeamId() == teamId){
                playersList.add(teamPlayer.getPlayerId());
            }
        }

        return playersList;
    }

    public String updateTeamOfPlayerByName(String name, int teamId){
        String oldTeam = "";
        String teamUpdated = "";
        String playerUpdated = "";

        try {
            Team newTeam = teamRepository.findByTeamId(teamId);

            for(TeamPlayer teamPlayer : teamPlayerRepository.findAll()){
                if(teamPlayer.getPlayerId().getPlayerName().equals(name)){
                    oldTeam = teamPlayer.getTeamId().getTeamName();
                    playerUpdated = teamPlayer.getPlayerId().getPlayerName();
                    teamPlayer.setTeamId(newTeam);
                    teamUpdated = teamPlayer.getTeamId().getTeamName();
                    teamPlayerRepository.save(teamPlayer);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "time do jogador "+playerUpdated+" alterado de "+oldTeam+" para "+teamUpdated;
    }

    public String updatePositionsPlayerByName(String name, int positionId){
        String oldPosition = "";
        String newPosition = "";
        String playerUpdated = "";

        try{
            Position position = positionRepository.findByPositionId(positionId);

            for(TeamPlayer teamPlayer : teamPlayerRepository.findAll()){
                if(teamPlayer.getPlayerId().getPlayerName().equals(name)){
                    oldPosition = teamPlayer.getPlayerId().getPosition().getPositionDescription();
                    playerUpdated = teamPlayer.getPlayerId().getPlayerName();
                    teamPlayer.getPlayerId().setPosition(position);
                    newPosition = teamPlayer.getPlayerId().getPosition().getPositionDescription();
                    teamPlayerRepository.save(teamPlayer);
                    break;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return "A posição do jogador "+playerUpdated+" passou de "+oldPosition+" para "+newPosition;
    }

    public String deleteTeamPlayerById(int id){
        try {
            for (TeamPlayer teamPlayer : teamPlayerRepository.findAll()){
                if(teamPlayer.getTeamPlayerId() == id){
                    teamPlayerRepository.deleteById(id);
                }
            }
            return "Deletado com Sucesso";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Erro ao deletar";
    }
}

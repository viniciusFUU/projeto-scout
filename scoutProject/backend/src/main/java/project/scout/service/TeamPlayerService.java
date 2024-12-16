package project.scout.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import project.scout.DTO.TeamDTO;
import project.scout.model.Player;
import project.scout.model.Team;
import project.scout.model.TeamPlayer;
import project.scout.repository.PlayerRepository;
import project.scout.repository.TeamPlayerRepository;
import project.scout.repository.TeamRepository;

@Service
public class TeamPlayerService {
    private final TeamPlayerRepository teamPlayerRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public TeamPlayerService(TeamPlayerRepository teamPlayerRepository, TeamRepository teamRepository, PlayerRepository playerRepository){
        this.teamPlayerRepository = teamPlayerRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
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

    public String updateTeamOfPlayer(int playerId, TeamDTO teamDTO){
        String oldTeam = "";
        String teamUpdated = "";
        String playerUpdated = "";

        try {
            TeamPlayer tryToUpdate = teamPlayerRepository.findByPlayer_Id(playerId);
            
            if(tryToUpdate != null){
                tryToUpdate.setTeamId(teamDTO.set);
                teamPlayerRepository.save(tryToUpdate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "time do jogador "+playerUpdated+" alterado de "+oldTeam+" para "+teamUpdated;
    }
}

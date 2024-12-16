package project.scout.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.scout.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{
    Optional<Player> findByPlayerName(String playerName);
    Player findByPlayerId(int playerId);
}
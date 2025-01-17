package project.scout.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.scout.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{
    Player findByPlayerName(String playerName);
    Player findByPlayerId(int playerId);

    @Query(value = """
            select 
            t.team_name,
            p.player_name,
            count(*) as scores
            from match_stats ms
            left join team_player tp
            on ms.team_player_id = tp.team_player_id
            left join player p
            on tp.player_id = p.player_id
            left join team t
            on tp.team_id = t.team_id
            group by t.team_name, p.player_name
            """, 
        nativeQuery = true)
    List<Object[]> getTopScores();
}
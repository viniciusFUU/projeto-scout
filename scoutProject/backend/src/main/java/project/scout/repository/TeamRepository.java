package project.scout.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.scout.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>{
    Team findByTeamId(int teamId);
    Team findByTeamName(String teamName);

    @Query(value = """
            select 
            t.team_name as teamName,
            p.player_name as playerName,
            count(*) as goal
            from match_stats ms
            left join team_player tp
            on ms.team_player_id = tp.team_player_id
            left join team t
            on tp.team_id = t.team_id
            left join player p
            on tp.player_id = p.player_id
            where ms.statistic_id = 1 and 
            t.team_id = :teamId
            group by t.team_name, p.player_name
            """, nativeQuery = true)
            List<Object[]> getTopScores(@Param("teamId") int teamId);
}
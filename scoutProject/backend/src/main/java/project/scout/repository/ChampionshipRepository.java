package project.scout.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.scout.model.Championship;

@Repository
public interface ChampionshipRepository extends JpaRepository<Championship, Integer>{
    Championship findByChampionshipId(int championshipId);
    Championship findByChampionshipName(String championshipName);

    @Query(value = """
        select 
        p.player_name as playerName,
        t.team_name as teamName,
        count(*) as goals
        from match_stats ms
        left join team_championship tc 
        on ms.team_championship_id = tc.team_championship_id
        left join team t
        on tc.team_id = t.team_id
        left join team_player tp
        on ms.team_player_id = tp.team_player_id
        left join player p
        on tp.player_id = p.player_id
        where tc.championship_id = :championshipId and ms.statistic_id = 1
        group by p.player_name, t.team_name
        """, nativeQuery = true)
    List<Object[]> getTopScores(@Param("championshipId") int championshipId);

    @Query(value = """
        select 
        p.player_name as playerName,
        t.team_name as teamName,
        count(*) as assists
        from match_stats ms
        left join team_championship tc 
        on ms.team_championship_id = tc.team_championship_id
        left join team t
        on tc.team_id = t.team_id
        left join team_player tp
        on ms.team_player_id = tp.team_player_id
        left join player p
        on tp.player_id = p.player_id
        where tc.championship_id = :championshipId and ms.statistic_id = 2
        group by p.player_name, t.team_name
        """, nativeQuery = true)
    List<Object[]> getTopAssistents(@Param("championshipId") int championshipId);

    @Query(value = """
        select 
        p.player_name as playerName,
        t.team_name as teamName,
        count(*) as passes
        from match_stats ms
        left join team_championship tc 
        on ms.team_championship_id = tc.team_championship_id
        left join team t
        on tc.team_id = t.team_id
        left join team_player tp
        on ms.team_player_id = tp.team_player_id
        left join player p
        on tp.player_id = p.player_id
        where tc.championship_id = :championshipId and ms.statistic_id = 3
        group by p.player_name, t.team_name
        """, nativeQuery = true)
    List<Object[]> getTopPassers(@Param("championshipId") int championshipId);
}
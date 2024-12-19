package project.scout.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchStatsDTO {
    private int matchId;
    private int teamPlayerId;
    private int teamChampionshipId;
    private int statisticId;
}

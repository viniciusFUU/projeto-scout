package project.scout.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamPlayerDTO {
    private int teamId;
    private int playerId;
    private String playerName;
    private int positionId;
}

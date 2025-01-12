package project.scout.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopScoresDTO {
    private String playerName;
    private String teamName;
    private int scores;
}

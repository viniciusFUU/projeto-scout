package project.scout.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamDTO {
    private String teamName;
    private String fieldToUpdate;
    private String newValue;
}

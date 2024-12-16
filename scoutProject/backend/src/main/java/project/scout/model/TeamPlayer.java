package project.scout.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "team_player")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teamPlayerId")
    private int teamPlayerId;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team teamId;
    
    @ManyToOne
    @JoinColumn(name = "playerId")
    private Player playerId;

    @Version
    private Integer version;
}

package project.scout.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "player")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playerId")
    private int playerId;

    @Column(name = "playerName")
    private String playerName;

    @Column(name = "playerBirthday")
    private String playerBirthday;
    
    @ManyToOne
    @JoinColumn(name = "positionId", nullable = false)
    private Position position;

    @Column(name = "playerWeigth")
    private double playerWeigth;

    @Column(name = "playerHeigth")
    private double playerHeigth;

    @Column(name = "playerNumber")
    private double playerNumber;
}
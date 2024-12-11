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
@Table(name = "team_championship")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamChampionship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teamChampionshipId ")
    private int teamChampionshipId;

    @ManyToOne
    @JoinColumn(name = "teamId", nullable = false)
    private Team teamId;

    @ManyToOne
    @JoinColumn(name = "championshipId", nullable = false)
    private Championship championshipId;

    @Column(name = "qtdInserido")
    private int qtdInserido;
}

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
@Table(name = "team_stats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teamStatsId")
    private int teamStatsId;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team teamId;

    @ManyToOne
    @JoinColumn(name = "statisticId")
    private Statistic statisticId;
}
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
@Table(name = "match_stats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matchStatsId")
    private int matchStatsId;

    @ManyToOne
    @JoinColumn(name = "matchId")
    private Match matchId;

    @ManyToOne
    @JoinColumn(name = "teamPlayerId")
    private TeamPlayer teamPlayerId;

    @ManyToOne
    @JoinColumn(name = "teamChampionshipId")
    private TeamChampionship teamChampionshipId;

    @ManyToOne
    @JoinColumn(name = "statisticId")
    private Statistic statisticId;
}
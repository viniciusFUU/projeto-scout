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
@Table(name = "championship_stats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChampionshipStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "championshipStatsId")
    private int championshipStatsId;

    @ManyToOne
    @JoinColumn(name = "championshipId")
    private Championship championshipId;

    @ManyToOne
    @JoinColumn(name = "statisticId")
    private Statistic statisticId;
}

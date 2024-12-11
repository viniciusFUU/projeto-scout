package project.scout.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "championship")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Championship{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "championshipId")
    private int championshipId;

    @Column(name = "championshipName")
    private String championshipName;

    @Column(name = "qtdTeams")
    private int qtdTeams;
}
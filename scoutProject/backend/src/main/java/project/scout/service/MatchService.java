package project.scout.service;

import org.springframework.stereotype.Service;

import project.scout.model.Match;
import project.scout.repository.MatchRepository;

@Service
public class MatchService {
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }

    public String createMatch(Match match){
        matchRepository.save(match);

        return "Partida criada com sucesso";
    }
}

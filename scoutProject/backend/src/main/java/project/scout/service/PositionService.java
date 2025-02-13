package project.scout.service;

import java.util.List;

import org.springframework.stereotype.Service;

import project.scout.model.Position;
import project.scout.repository.PositionRepository;

@Service
public class PositionService {
    private PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository){
        this.positionRepository = positionRepository;
    }

    public List<Position> getAllPositions(){
        return positionRepository.findAll();
    }

    public Position getPositionByDescription(String posicao){
        Position position = positionRepository.findByPositionDescription(posicao);

        return position;
    }
}
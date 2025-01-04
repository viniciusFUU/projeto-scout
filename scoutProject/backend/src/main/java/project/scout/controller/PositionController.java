package project.scout.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.scout.model.Position;
import project.scout.service.PositionService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/scout/position")
public class PositionController {
    private final PositionService positionService;

    public PositionController(PositionService positionService){
        this.positionService = positionService;
    }

    @GetMapping    
    public List<Position> getAllPosition(){
        return positionService.getAllPositions();
    }
}

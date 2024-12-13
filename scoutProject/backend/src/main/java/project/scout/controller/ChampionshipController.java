package project.scout.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.scout.DTO.ChampionshipDTO;
import project.scout.model.Championship;
import project.scout.service.ChampionshipService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/scout/championship")
public class ChampionshipController {
    private final ChampionshipService championshipService;

    public ChampionshipController(ChampionshipService championshipService){
        this.championshipService = championshipService;
    }

    @PostMapping
    public String createChampionship(@RequestBody Championship championship) {
        return championshipService.createChampionship(championship);
    }

    @GetMapping
    public List<Championship> getAllChampionship() {
        return championshipService.getAllChampionship();
    }

    @GetMapping("/name")
    public String getChampionshipByName(@RequestParam String param) {
        return championshipService.getChampionshipByName(param);
    }

    @PutMapping
    public String updateChampionshipByName(@RequestBody ChampionshipDTO championshipDTO) {        
        return championshipService.updateChampionshipByName(championshipDTO.getChampionshipName(), championshipDTO.getFieldToUpdate(), championshipDTO.getNewValue());
    }

    @DeleteMapping("/name")
    public String deleteByName(@RequestParam String name){
        return championshipService.deleteChampionshipByName(name);
    }
}

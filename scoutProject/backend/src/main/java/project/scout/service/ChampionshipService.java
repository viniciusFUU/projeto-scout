package project.scout.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import project.scout.DTO.ChampionshipStatsDTO;
import project.scout.model.Championship;
import project.scout.repository.ChampionshipRepository;

@Service
public class ChampionshipService {
    private final ChampionshipRepository championshipRepository;

    public ChampionshipService(ChampionshipRepository championshipRepository){
        this.championshipRepository = championshipRepository;
    }

    public String createChampionship(Championship championship){
        championshipRepository.save(championship);

        return championship.getChampionshipName()+" criado com sucesso";
    }

    public List<Championship> getAllChampionship(){
        List<Championship> championshipList = championshipRepository.findAll();

        return championshipList;
    }

    public String getChampionshipByName(String name){
        String championshipFound = "";

        try {
            for(Championship championship : championshipRepository.findAll()){
                if (championship.getChampionshipName().equals(name)) {
                    championshipFound = championship.getChampionshipName();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (championshipFound != ""){
            return championshipFound+" existe na lista";
        }

        return "Não existe nenhum championship com o nome "+name;
    }

    public String updateChampionshipByName(String championshipName, String fieldToUpdate, String newValue){
        try {
            for(Championship championship : championshipRepository.findAll()){
                if(championship.getChampionshipName().equals(championshipName)){
                    switch (fieldToUpdate) {
                        case "championshipName":
                            championship.setChampionshipName(newValue);
                            break;
                            case "qtdTeams":
                            Integer value = Integer.parseInt(newValue);
                            championship.setChampionshipId(value);
                            default:
                            break;
                        }
                        championshipRepository.save(championship);
                    }
                }
            return fieldToUpdate+" do champiship "+championshipName+" foi atualizado para "+newValue;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Não existe time com o nome "+championshipName;
    }

    public String deleteChampionshipByName(String name){
        try {
            for(Championship championship : championshipRepository.findAll()){
                if(championship.getChampionshipName().equals(name)){
                    championshipRepository.delete(championship);
                }
            }
            return "Championship: "+name+ "Deletado com sucesso.";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Não existe o campeonaro "+name;
    }

    public List<ChampionshipStatsDTO> getTopScores(String championshipName){
        List<ChampionshipStatsDTO> topScores = new ArrayList<>();
        Championship championship = championshipRepository.findByChampionshipName(championshipName);
        
        List<Object[]> results = championshipRepository.getTopScores(championship.getChampionshipId());
        
        if(results != null){
            try {
                for (Object[] row : results) {
                    ChampionshipStatsDTO championshipStatsDTO = new ChampionshipStatsDTO();
                    String playerName = (String) row[0];
                    championshipStatsDTO.setPlayerName(playerName);
                    String teamName = (String) row[1];
                    championshipStatsDTO.setTeamName(teamName);
                    Integer goals = ((Number) row[2]).intValue();
                    championshipStatsDTO.setScores(goals);
                    topScores.add(championshipStatsDTO);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return topScores;
    }
}

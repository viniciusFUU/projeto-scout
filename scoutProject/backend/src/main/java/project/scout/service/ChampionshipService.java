package project.scout.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import project.scout.DTO.TopAssistents;
import project.scout.DTO.TopScoresDTO;
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

    public List<TopScoresDTO> getTopScores(String championshipName){
        List<TopScoresDTO> topScores = new ArrayList<>();
        Championship championship = championshipRepository.findByChampionshipName(championshipName);
        
        List<Object[]> results = championshipRepository.getTopScores(championship.getChampionshipId());
        
        if(results != null){
            try {
                for (Object[] row : results) {
                    TopScoresDTO data = new TopScoresDTO();
                    String playerName = (String) row[0];
                    data.setPlayerName(playerName);
                    String teamName = (String) row[1];
                    data.setTeamName(teamName);
                    Integer goals = ((Number) row[2]).intValue();
                    data.setScores(goals);
                    topScores.add(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return topScores;
    }

    public List<TopAssistents> getTopAssistents(String championshipName){
        List<TopAssistents> topAssistents = new ArrayList<>();
        Championship championship = championshipRepository.findByChampionshipName(championshipName);

        List<Object[]> results = championshipRepository.getTopAssistents(championship.getChampionshipId());

        if(results != null){
            try {
                for(Object[] row : results){
                    TopAssistents data = new TopAssistents();
                    String playerName = (String) row[0];
                    data.setPlayerName(playerName);
                    String teamName = (String) row[1];
                    data.setTeamName(teamName);
                    int assistences = ((Number) row[2]).intValue();
                    data.setAssistent(assistences);
                    topAssistents.add(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return topAssistents;
    }
}

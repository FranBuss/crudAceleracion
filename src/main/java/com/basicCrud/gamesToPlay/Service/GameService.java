package com.basicCrud.gamesToPlay.Service;

import com.basicCrud.gamesToPlay.Entity.Game;
import com.basicCrud.gamesToPlay.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public Game addGame(Game game){
        return gameRepository.save(game);
    }

    public void updateGameToFinished(String id){
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isPresent()){
            Game game = optionalGame.get();
            game.setFinished(1);
            gameRepository.save(game);
        }
    }

    public void updateGameToPlayed(String id){
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isPresent()){
            Game game = optionalGame.get();
            game.setPlayed(1);
            gameRepository.save(game);
        }
    }

    public boolean deleteGame(String id){
        try {
            gameRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    public ArrayList<Game> getAll(){
        return (ArrayList<Game>) gameRepository.findAll();
    }

    public Optional<Game> getById(String id){
        return gameRepository.findById(id);
    }

    public ArrayList<Game> listByCategory(String category){
        return gameRepository.findAllByCategory(category);
    }

    public ArrayList<Game> listByPlayed(int played){
        return gameRepository.findAllByPlayed(played);
    }


    public ArrayList<Game> listByFinished(int finished){
        return gameRepository.findAllByFinished(finished);
    }


}

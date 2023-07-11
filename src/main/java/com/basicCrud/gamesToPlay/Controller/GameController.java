package com.basicCrud.gamesToPlay.Controller;

import com.basicCrud.gamesToPlay.Entity.Game;
import com.basicCrud.gamesToPlay.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/list")
    public ArrayList<Game> getGames(){
        return gameService.getAll();
    }

    @GetMapping("/{gameId}")
    public Optional<Game> getGameById(@PathVariable("gameId") String id){
        return gameService.getById(id);
    }

    @GetMapping("/category")
    public ArrayList<Game> getGamesByCategory(@RequestParam("category") String category){
        return gameService.listByCategory(category);
    }

    @GetMapping("/finished")
    public ArrayList<Game> getFinishedGames(@RequestParam("type") int type){
        if (type >= 0 && type <= 1){
            return gameService.listByFinished(type);
        }
        return null;
    }

    @GetMapping("/played")
    public ArrayList<Game> getPlayedGames(@RequestParam("type") int type){
        if (type >= 0 && type <= 1){
            return gameService.listByPlayed(type);
        }
        return null;
    }

    @PostMapping("/saveGame")
    public Game saveGame(@RequestBody Game game){
        return gameService.addGame(game);
    }

    @PutMapping("/finished/{gameId}")
    public void updateGameToFinished(@PathVariable("gameId") String id){
        gameService.updateGameToFinished(id);
    }

    @PutMapping("/played/{gameId}")
    public void updateGameToPlayed(@PathVariable("gameId") String id){
        gameService.updateGameToPlayed(id);
    }

    @DeleteMapping("/deleteGame/{gameId}")
    public String deleteGame(@PathVariable("gameId") String id){
        boolean ok = gameService.deleteGame(id);
        if (ok) {
            return "Se elimino el juego con id : " + id;
        } else {
            return "No se pudo eliminar el juego con id : " + id;
        }
    }

}

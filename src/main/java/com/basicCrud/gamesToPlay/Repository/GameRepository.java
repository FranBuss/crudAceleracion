package com.basicCrud.gamesToPlay.Repository;

import com.basicCrud.gamesToPlay.Entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface GameRepository extends JpaRepository<Game, String> {

    public ArrayList<Game> findAllByCategory(String category);

    public ArrayList<Game> findAllByFinished(int finished);

    public ArrayList<Game> findAllByPlayed(int played);

}

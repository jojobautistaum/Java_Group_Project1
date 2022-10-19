package com.project1.Summative1jojoyinara.controller;

import com.project1.Summative1jojoyinara.model.Game;
import com.project1.Summative1jojoyinara.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    GameRepository gameRepo;

    @GetMapping()
    public List<Game> getGames(){
        return gameRepo.findAll();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable Integer id){
        Optional<Game> returnVal = gameRepo.findById(id);
        if(returnVal.isPresent()){
            return returnVal.get();
        } else{
            return null;
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody @Valid Game game){
        return gameRepo.save(game);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody @Valid Game game){
        gameRepo.save(game);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable Integer id){
        gameRepo.deleteById(id);
    }

    @GetMapping("/studio/{studio}")
    public List<Game> getGamesByStudio(@PathVariable String studio){
        return gameRepo.findAllGameByStudio(studio);
    }

    @GetMapping("/esrbRating/{esrbRating}")
    public List<Game> getGamesByEsrbRating(@PathVariable String esrbRating){
        return gameRepo.findAllGameByEsrbRating(esrbRating);
    }

    @GetMapping("/title/{title}")
    public List<Game> getGamesByTitle(@PathVariable String title){
        return gameRepo.findAllGameByTitle(title);
    }






}

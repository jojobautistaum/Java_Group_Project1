package com.project1.Summative1jojoyinara.repository;

import com.project1.Summative1jojoyinara.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
   List<Game> findAllGameByStudio(String studio);
   List<Game> findAllGameByEsrbRating(String esrbRating);
   List<Game> findAllGameByTitle(String title);
}

package com.project1.Summative1jojoyinara.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.Summative1jojoyinara.model.Game;
import com.project1.Summative1jojoyinara.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameRepository gameRepo;
    private ObjectMapper mapper = new ObjectMapper();
    private Game game1;
    private String game1Json;
    private List<Game> allGames = new ArrayList<>();
    private String allGamesJson;

    @Before
    public void setup() throws Exception {
        game1 = new Game();
        game1.setGameId(1);
        game1.setTitle("starArcade");
        game1.setEsrbRating("TEEN");
        game1.setDescription("space game for star citizen");
        game1.setPrice(15.99);
        game1.setStudio("dreamWorks");
        game1.setQuantity(200);

        game1Json = mapper.writeValueAsString(game1);

        Game game2 = new Game();
        game2.setGameId(2);
        game2.setTitle("photoEditing");
        game2.setEsrbRating("TEEN");
        game2.setDescription("photo editing");
        game2.setPrice(14.99);
        game2.setStudio("Soul");
        game2.setQuantity(200);
        allGames.add(game1);
        allGames.add(game2);

        allGamesJson = mapper.writeValueAsString(allGames);
    }

    @Test
    public void shouldCreateNewGameOnPostingRequest() throws Exception{
        Game inputGame = new Game();
        inputGame.setGameId(1);
        inputGame.setTitle("starArcade");
        inputGame.setEsrbRating("TEEN");
        inputGame.setDescription("space game for star citizen");
        inputGame.setPrice(15.99);
        inputGame.setStudio("dreamWorks");
        inputGame.setQuantity(200);
        String inputGameJson = mapper.writeValueAsString(inputGame);

        doReturn (game1).when(gameRepo).save(inputGame);

        mockMvc.perform(
                        post("/game")
                                .content(inputGameJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(game1Json));
        }


}
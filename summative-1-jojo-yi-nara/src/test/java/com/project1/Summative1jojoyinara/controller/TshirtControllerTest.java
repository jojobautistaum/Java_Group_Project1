package com.project1.Summative1jojoyinara.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.Summative1jojoyinara.model.Tshirt;
import com.project1.Summative1jojoyinara.repository.TshirtRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TshirtController.class)
public class TshirtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TshirtRepository tshirtRepository;

    private ObjectMapper mapper = new ObjectMapper();

    private Tshirt inputTshirt1;
    private Tshirt outputTshirt1;
    private Tshirt inputTshirt2;
    private Tshirt outputTshirt2;
    private Tshirt inputTshirt3;
    private Tshirt outputTshirt3;

    private List<Tshirt> tshirtList;
    private List<Tshirt> tshirtListByColor;
    private List<Tshirt> tshirtListBySize;

    private String inputJson;
    private String outputJson;

    @Before
    public void setUp() throws Exception {
        inputTshirt1 = new Tshirt(null,"XS", "red", "Plain", 20.00, 100);
        outputTshirt1 = new Tshirt(1,"XS", "red", "Plain", 20.00, 100);

        inputTshirt2 = new Tshirt(null,"M", "white", "Nike Logo", 30.00, 100);
        outputTshirt2 = new Tshirt(31,"M", "white", "Nike Logo", 30.00, 100);

        inputTshirt3 = new Tshirt(null,"XS", "white", "Prada", 150.00, 100);
        outputTshirt3 = new Tshirt(101,"XS", "white", "Prada", 150.00, 100);

        tshirtList = new ArrayList<>(Arrays.asList(
                outputTshirt1,
                outputTshirt2,
                outputTshirt3
        ));

        tshirtListByColor = new ArrayList<>(Arrays.asList(
                outputTshirt2,
                outputTshirt3
        ));

        tshirtListBySize = new ArrayList<>(Arrays.asList(
                outputTshirt1,
                outputTshirt3
        ));
    }

    // ------------ MockMVC test for successful response ---------------
    // -----------------------------------------------------------------

    @Test
    public void shouldReturnOneTshirtAfterPostMethod() throws Exception {
        doReturn(outputTshirt2).when(tshirtRepository).save(inputTshirt2);

        inputJson = mapper.writeValueAsString(inputTshirt2);
        outputJson = mapper.writeValueAsString(outputTshirt2);

        mockMvc.perform(post("/t-shirt")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnAllTshirts() throws Exception {
        doReturn(tshirtList).when(tshirtRepository).findAll();
        outputJson = mapper.writeValueAsString(tshirtList);

        mockMvc.perform(get("/t-shirt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnOneTshirt() throws Exception {
        doReturn(Optional.of(outputTshirt2)).when(tshirtRepository).findById(31);
        outputJson = mapper.writeValueAsString(outputTshirt2);

        mockMvc.perform(get("/t-shirt/31"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldUpdateTshirtAfterPutMethod() throws Exception {
        inputJson = mapper.writeValueAsString(inputTshirt1);

        mockMvc.perform(put("/t-shirt")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteTshirtAfterDeleteMethod() throws Exception {
          doReturn(Optional.ofNullable(outputTshirt1)).when(tshirtRepository).findById(1);

        mockMvc.perform(
                delete("/t-shirt/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnAllTshirtsByColor() throws Exception {
        doReturn(tshirtListByColor).when(tshirtRepository).findTShirtByColor("white");
        outputJson = mapper.writeValueAsString(tshirtListByColor);

        mockMvc.perform(get("/t-shirt/color/white"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnAllTshirtsBySize() throws Exception {
        doReturn(tshirtListBySize).when(tshirtRepository).findTShirtBySize("XS");
        outputJson = mapper.writeValueAsString(tshirtListBySize);

        mockMvc.perform(get("/t-shirt/size/XS"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    // -------------- MockMVC test for invalid request -----------------
    // -----------------------------------------------------------------
    @Test
    public void shouldReturn422ForInvalidInputForTshirtModel() throws Exception {
        Map<String, String> inputMap = new HashMap<>();
        String inputMapJson;

        inputMap.put("size", "XS");
        inputMap.put("color", "red");
        inputMap.put("description", "Plain");
        inputMap.put("quantity", "a");
        inputMap.put("price", "-2.00");

        inputMapJson = mapper.writeValueAsString(inputMap);

        mockMvc.perform(post("/t-shirt")
                        .content(inputMapJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422ForMissingInputForTshirtModel() throws Exception {
        inputTshirt1.setSize(null);
//        inputTshirt1.setColor(null);
//        inputTshirt1.setDescription(null);
//        inputTshirt1.setQuantity(-5);
//        inputTshirt1.setPrice(-10.00);

        inputJson = mapper.writeValueAsString(inputTshirt1);

        mockMvc.perform(post("/t-shirt")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn404IWhenGetTshirtByIdNotFound() throws Exception {
        mockMvc.perform(get("/t-shirt/15"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturn404IWhenDeleteTshirtByIdNotFound() throws Exception {
        doReturn(Optional.ofNullable(null)).when(tshirtRepository).findById(203);

        mockMvc.perform(
                        delete("/t-shirt/203"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
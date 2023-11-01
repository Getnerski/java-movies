package br.com.movies.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AwardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAwardById() throws Exception {
        Long awardId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/awards/" + awardId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value("1980"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winner.title").value("Can't Stop the Music"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winner.studios[0].name").value("Associated Film Distribution"));
    }

    @Test
    public void testGetNonExistentAward() throws Exception {
        Long nonExistentAwardId = 999L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/awards/" + nonExistentAwardId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testCreateAward() throws Exception {
        String jsonBody = "{ \"year\": \"2023\", \"winner\": { \"title\": \"New Movie\", \"studios\": [ { \"name\": \"Studio A\" } ] } }";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/awards")
                        .content(jsonBody)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdateAward() throws Exception {
        Long awardIdToUpdate = 1L;
        String jsonBody = "{ \"year\": \"2022\", \"winner\": { \"title\": \"Updated Movie\", \"studios\": [ { \"name\": \"Studio B\" } ] } }";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/awards/" + awardIdToUpdate)
                        .content(jsonBody)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteAward() throws Exception {
        Long awardIdToDelete = 2L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/awards/" + awardIdToDelete))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

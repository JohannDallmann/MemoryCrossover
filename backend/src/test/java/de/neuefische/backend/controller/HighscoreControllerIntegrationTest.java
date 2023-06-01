package de.neuefische.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HighscoreControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DirtiesContext
    void testAddScore() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/randm/highscore"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/randm/highscore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "playerName": "Peter Mustermann",
                            "score": 1
                        }
                        """));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/randm/highscore/sorted/score/asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].playerName").value("Peter Mustermann"))
                .andExpect(jsonPath("$[0].score").value(1));

    }

    @Test
    @DirtiesContext
    void testGetAllHighscores() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/randm/highscore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "playerName": "Peter Mustermann",
                            "score": 1
                        }
                        """));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/randm/highscore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "playerName": "Erika Mustermann",
                            "score": 2
                        }
                        """));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/randm/highscore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "playerName": "Oliver Hofrichter",
                            "score": 3
                        }
                        """));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/randm/highscore"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "playerName": "Peter Mustermann",
                                "score": 1
                            },
                            {
                                "playerName": "Erika Mustermann",
                                "score": 2
                            },
                            {
                                "playerName": "Oliver Hofrichter",
                                "score": 3
                            }
                        ]
                        """));
    }

    @Test
    @DirtiesContext
    void testGetSortedHighscoresAscending() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/randm/highscore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "playerName": "Peter Mustermann",
                            "score": 1
                        }
                        """));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/randm/highscore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "playerName": "Erika Mustermann",
                            "score": 2
                        }
                        """));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/randm/highscore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "playerName": "Oliver Hofrichter",
                            "score": 3
                        }
                        """));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/randm/highscore/sorted/score/asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].playerName").value("Peter Mustermann"))
                .andExpect(jsonPath("$[0].score").value(1))
                .andExpect(jsonPath("$[1].playerName").value("Erika Mustermann"))
                .andExpect(jsonPath("$[1].score").value(2))
                .andExpect(jsonPath("$[2].playerName").value("Oliver Hofrichter"))
                .andExpect(jsonPath("$[2].score").value(3));
    }

    @Test
    @DirtiesContext
    void testGetSortedHighscoresDescending() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/randm/highscore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "playerName": "Peter Mustermann",
                            "score": 1
                        }
                        """));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/randm/highscore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "playerName": "Erika Mustermann",
                            "score": 2
                        }
                        """));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/randm/highscore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "playerName": "Oliver Hofrichter",
                            "score": 3
                        }
                        """));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/randm/highscore/sorted/score/desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].playerName").value("Oliver Hofrichter"))
                .andExpect(jsonPath("$[0].score").value(3))
                .andExpect(jsonPath("$[1].playerName").value("Erika Mustermann"))
                .andExpect(jsonPath("$[1].score").value(2))
                .andExpect(jsonPath("$[2].playerName").value("Peter Mustermann"))
                .andExpect(jsonPath("$[2].score").value(1));
    }

    @Test
    @DirtiesContext
    void testGetHighscoresSortedByTimestampAscending() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/randm/highscore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "playerName": "Peter Mustermann",
                            "score": 3
                        }
                        """));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/randm/highscore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "playerName": "Erika Mustermann",
                            "score": 2
                        }
                        """));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/randm/highscore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "playerName": "Oliver Hofrichter",
                            "score": 1
                        }
                        """));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/randm/highscore/sorted/timestamp/asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].playerName").value("Peter Mustermann"))
                .andExpect(jsonPath("$[0].score").value(3))
                .andExpect(jsonPath("$[1].playerName").value("Erika Mustermann"))
                .andExpect(jsonPath("$[1].score").value(2))
                .andExpect(jsonPath("$[2].playerName").value("Oliver Hofrichter"))
                .andExpect(jsonPath("$[2].score").value(1));
    }

    @Test
    @DirtiesContext
    void testGetHighscoresSortedByTimestampDescending() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/randm/highscore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "playerName": "Peter Mustermann",
                            "score": 3
                        }
                        """));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/randm/highscore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "playerName": "Erika Mustermann",
                            "score": 2
                        }
                        """));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/randm/highscore")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "playerName": "Oliver Hofrichter",
                            "score": 1
                        }
                        """));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/randm/highscore/sorted/timestamp/desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].playerName").value("Oliver Hofrichter"))
                .andExpect(jsonPath("$[0].score").value(1))
                .andExpect(jsonPath("$[1].playerName").value("Erika Mustermann"))
                .andExpect(jsonPath("$[1].score").value(2))
                .andExpect(jsonPath("$[2].playerName").value("Peter Mustermann"))
                .andExpect(jsonPath("$[2].score").value(3));
    }
}
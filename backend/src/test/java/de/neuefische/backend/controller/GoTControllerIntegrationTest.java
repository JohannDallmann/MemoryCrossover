package de.neuefische.backend.controller;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GoTControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    private static MockWebServer mockWebServer;

    @BeforeAll
    static void beforeAll() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @DynamicPropertySource
    static void backendProperties(DynamicPropertyRegistry registry) {
        registry.add("gameofthrones.url", () -> mockWebServer.url("/").toString());
    }

    @Test
    void when_GetAllCharacters_is_called_then_return_is_ok() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setHeader("Content-Type", "application/json")
                .setBody("""
                        [
                            {
                              "id": 0,
                              "firstName": "Daenerys",
                              "lastName": "Targaryen",
                              "fullName": "Daenerys Targaryen",
                              "title": "Mother of Dragons",
                              "family": "House Targaryen",
                              "image": "daenerys.jpg",
                              "imageUrl": "https://thronesapi.com/assets/images/daenerys.jpg"
                            },
                            {
                              "id": 1,
                              "firstName": "Samwell",
                              "lastName": "Tarly",
                              "fullName": "Samwell Tarly",
                              "title": "Maester",
                              "family": "House Tarly",
                              "image": "sam.jpg",
                              "imageUrl": "https://thronesapi.com/assets/images/sam.jpg"
                            }
                        ]
                        """));


        mockMvc.perform(get("/api/got/characters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(0))
                .andExpect(jsonPath("$[0].fullName").value("Daenerys Targaryen"))
                .andExpect(jsonPath("$[0].family").value("House Targaryen"))
                .andExpect(jsonPath("$[0].imageUrl").value("https://thronesapi.com/assets/images/daenerys.jpg"))
                .andExpect(jsonPath("$[1].id").value(1))
                .andExpect(jsonPath("$[1].fullName").value("Samwell Tarly"))
                .andExpect(jsonPath("$[1].family").value("House Tarly"))
                .andExpect(jsonPath("$[1].imageUrl").value("https://thronesapi.com/assets/images/sam.jpg"))
        ;

    }

    @AfterAll
    static void afterAll() throws IOException {
        mockWebServer.shutdown();
    }

}
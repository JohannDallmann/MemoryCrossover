package de.neuefische.backend.controller;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RandMControllerIntegrationTest {

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
        registry.add("rickandmorty.url", () -> mockWebServer.url("/").toString());
    }

    @Test
    @WithMockUser(username = "user", password = "123")
    void when_GetAllCharacters_is_called_then_return_is_ok() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setHeader("Content-Type", "application/json")
                .setBody("""
                        {
                                                    "info":{
                                                       "count":826,
                                                       "pages":42,
                                                       "next":"https://rickandmortyapi.com/api/character?page=2",
                                                       "prev":null
                                                    },
                                                    "results":[
                                                       {
                                                          "id":1,
                                                          "name":"Peter Mustermann",
                                                          "status":"Alive",
                                                          "species":"Human",
                                                          "type":"",
                                                          "gender":"Male",
                                                          "origin":{
                                                             "name":"Earth (C-137)",
                                                             "url":"https://rickandmortyapi.com/api/location/1"
                                                          },
                                                          "location":{
                                                             "name":"Citadel of Ricks",
                                                             "url":"https://rickandmortyapi.com/api/location/3"
                                                          },
                                                          "image":"https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                                          "episode":[
                                                             "https://rickandmortyapi.com/api/episode/1",
                                                             "https://rickandmortyapi.com/api/episode/2"
                                                          ],
                                                          "url":"https://rickandmortyapi.com/api/character/1",
                                                          "created":"2017-11-04T18:48:46.250Z"
                                                       },
                                                       {
                                                          "id":2,
                                                          "name":"Morty Smith",
                                                          "status":"Alive",
                                                          "species":"Human",
                                                          "type":"",
                                                          "gender":"Male",
                                                          "origin":{
                                                             "name":"unknown",
                                                             "url":""
                                                          },
                                                          "location":{
                                                             "name":"Citadel of Ricks",
                                                             "url":"https://rickandmortyapi.com/api/location/3"
                                                          },
                                                          "image":"https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                                                          "episode":[
                                                             "https://rickandmortyapi.com/api/episode/1",
                                                             "https://rickandmortyapi.com/api/episode/2"
                                                          ],
                                                          "url":"https://rickandmortyapi.com/api/character/2",
                                                          "created":"2017-11-04T18:50:21.651Z"
                                                       },
                                                       {
                                                          "id":20,
                                                          "name":"Ants in my Eyes Johnson",
                                                          "status":"unknown",
                                                          "species":"Human",
                                                          "type":"Human with ants in his eyes",
                                                          "gender":"Male",
                                                          "origin":{
                                                             "name":"unknown",
                                                             "url":""
                                                          },
                                                          "location":{
                                                             "name":"Interdimensional Cable",
                                                             "url":"https://rickandmortyapi.com/api/location/6"
                                                          },
                                                          "image":"https://rickandmortyapi.com/api/character/avatar/20.jpeg",
                                                          "episode":[
                                                             "https://rickandmortyapi.com/api/episode/8"
                                                          ],
                                                          "url":"https://rickandmortyapi.com/api/character/20",
                                                          "created":"2017-11-04T22:34:53.659Z"
                                                       }
                                                    ]
                                                 }
                        """));

        mockWebServer.enqueue(new MockResponse()
                .setHeader("Content-Type", "application/json")
                .setBody("""
                        {
                                                    "info":{
                                                       "count":826,
                                                       "pages":42,
                                                       "next":null,
                                                       "prev":"https://rickandmortyapi.com/api/character?page=1"
                                                    },
                                                    "results":[
                                                       {
                                                          "id":1,
                                                          "name":"Peter Mustermann",
                                                          "status":"Alive",
                                                          "species":"Human",
                                                          "type":"",
                                                          "gender":"Male",
                                                          "origin":{
                                                             "name":"Earth (C-137)",
                                                             "url":"https://rickandmortyapi.com/api/location/1"
                                                          },
                                                          "location":{
                                                             "name":"Citadel of Ricks",
                                                             "url":"https://rickandmortyapi.com/api/location/3"
                                                          },
                                                          "image":"https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                                          "episode":[
                                                             "https://rickandmortyapi.com/api/episode/1",
                                                             "https://rickandmortyapi.com/api/episode/2"
                                                          ],
                                                          "url":"https://rickandmortyapi.com/api/character/1",
                                                          "created":"2017-11-04T18:48:46.250Z"
                                                       },
                                                       {
                                                          "id":2,
                                                          "name":"Morty Smith",
                                                          "status":"Alive",
                                                          "species":"Human",
                                                          "type":"",
                                                          "gender":"Male",
                                                          "origin":{
                                                             "name":"unknown",
                                                             "url":""
                                                          },
                                                          "location":{
                                                             "name":"Citadel of Ricks",
                                                             "url":"https://rickandmortyapi.com/api/location/3"
                                                          },
                                                          "image":"https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                                                          "episode":[
                                                             "https://rickandmortyapi.com/api/episode/1",
                                                             "https://rickandmortyapi.com/api/episode/2"
                                                          ],
                                                          "url":"https://rickandmortyapi.com/api/character/2",
                                                          "created":"2017-11-04T18:50:21.651Z"
                                                       },
                                                       {
                                                          "id":20,
                                                          "name":"Ants in my Eyes Johnson",
                                                          "status":"unknown",
                                                          "species":"Human",
                                                          "type":"Human with ants in his eyes",
                                                          "gender":"Male",
                                                          "origin":{
                                                             "name":"unknown",
                                                             "url":""
                                                          },
                                                          "location":{
                                                             "name":"Interdimensional Cable",
                                                             "url":"https://rickandmortyapi.com/api/location/6"
                                                          },
                                                          "image":"https://rickandmortyapi.com/api/character/avatar/20.jpeg",
                                                          "episode":[
                                                             "https://rickandmortyapi.com/api/episode/8"
                                                          ],
                                                          "url":"https://rickandmortyapi.com/api/character/20",
                                                          "created":"2017-11-04T22:34:53.659Z"
                                                       }
                                                    ]
                                                 }
                        """));

        mockMvc.perform(get("/api/randm/characters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Peter Mustermann"))
        ;

    }

    @Test
    @WithMockUser(username = "user", password = "123")
    void when_fillCharactersFromApi_is_called_then_return_is_ok() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setHeader("Content-Type", "application/json")
                .setBody("""
                        {
                                                    "info":{
                                                       "count":826,
                                                       "pages":42,
                                                       "next":"https://rickandmortyapi.com/api/character?page=2",
                                                       "prev":null
                                                    },
                                                    "results":[
                                                       {
                                                          "id":1,
                                                          "name":"Peter Mustermann",
                                                          "status":"Alive",
                                                          "species":"Human",
                                                          "type":"",
                                                          "gender":"Male",
                                                          "origin":{
                                                             "name":"Earth (C-137)",
                                                             "url":"https://rickandmortyapi.com/api/location/1"
                                                          },
                                                          "location":{
                                                             "name":"Citadel of Ricks",
                                                             "url":"https://rickandmortyapi.com/api/location/3"
                                                          },
                                                          "image":"https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                                          "episode":[
                                                             "https://rickandmortyapi.com/api/episode/1",
                                                             "https://rickandmortyapi.com/api/episode/2"
                                                          ],
                                                          "url":"https://rickandmortyapi.com/api/character/1",
                                                          "created":"2017-11-04T18:48:46.250Z"
                                                       },
                                                       {
                                                          "id":2,
                                                          "name":"Morty Smith",
                                                          "status":"Alive",
                                                          "species":"Human",
                                                          "type":"",
                                                          "gender":"Male",
                                                          "origin":{
                                                             "name":"unknown",
                                                             "url":""
                                                          },
                                                          "location":{
                                                             "name":"Citadel of Ricks",
                                                             "url":"https://rickandmortyapi.com/api/location/3"
                                                          },
                                                          "image":"https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                                                          "episode":[
                                                             "https://rickandmortyapi.com/api/episode/1",
                                                             "https://rickandmortyapi.com/api/episode/2"
                                                          ],
                                                          "url":"https://rickandmortyapi.com/api/character/2",
                                                          "created":"2017-11-04T18:50:21.651Z"
                                                       },
                                                       {
                                                          "id":20,
                                                          "name":"Ants in my Eyes Johnson",
                                                          "status":"unknown",
                                                          "species":"Human",
                                                          "type":"Human with ants in his eyes",
                                                          "gender":"Male",
                                                          "origin":{
                                                             "name":"unknown",
                                                             "url":""
                                                          },
                                                          "location":{
                                                             "name":"Interdimensional Cable",
                                                             "url":"https://rickandmortyapi.com/api/location/6"
                                                          },
                                                          "image":"https://rickandmortyapi.com/api/character/avatar/20.jpeg",
                                                          "episode":[
                                                             "https://rickandmortyapi.com/api/episode/8"
                                                          ],
                                                          "url":"https://rickandmortyapi.com/api/character/20",
                                                          "created":"2017-11-04T22:34:53.659Z"
                                                       }
                                                    ]
                                                 }
                        """));

        mockWebServer.enqueue(new MockResponse()
                .setHeader("Content-Type", "application/json")
                .setBody("""
                        {
                                                    "info":{
                                                       "count":826,
                                                       "pages":42,
                                                       "next":null,
                                                       "prev":"https://rickandmortyapi.com/api/character?page=1"
                                                    },
                                                    "results":[
                                                       {
                                                          "id":1,
                                                          "name":"Peter Mustermann",
                                                          "status":"Alive",
                                                          "species":"Human",
                                                          "type":"",
                                                          "gender":"Male",
                                                          "origin":{
                                                             "name":"Earth (C-137)",
                                                             "url":"https://rickandmortyapi.com/api/location/1"
                                                          },
                                                          "location":{
                                                             "name":"Citadel of Ricks",
                                                             "url":"https://rickandmortyapi.com/api/location/3"
                                                          },
                                                          "image":"https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                                          "episode":[
                                                             "https://rickandmortyapi.com/api/episode/1",
                                                             "https://rickandmortyapi.com/api/episode/2"
                                                          ],
                                                          "url":"https://rickandmortyapi.com/api/character/1",
                                                          "created":"2017-11-04T18:48:46.250Z"
                                                       },
                                                       {
                                                          "id":2,
                                                          "name":"Morty Smith",
                                                          "status":"Alive",
                                                          "species":"Human",
                                                          "type":"",
                                                          "gender":"Male",
                                                          "origin":{
                                                             "name":"unknown",
                                                             "url":""
                                                          },
                                                          "location":{
                                                             "name":"Citadel of Ricks",
                                                             "url":"https://rickandmortyapi.com/api/location/3"
                                                          },
                                                          "image":"https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                                                          "episode":[
                                                             "https://rickandmortyapi.com/api/episode/1",
                                                             "https://rickandmortyapi.com/api/episode/2"
                                                          ],
                                                          "url":"https://rickandmortyapi.com/api/character/2",
                                                          "created":"2017-11-04T18:50:21.651Z"
                                                       },
                                                       {
                                                          "id":20,
                                                          "name":"Ants in my Eyes Johnson",
                                                          "status":"unknown",
                                                          "species":"Human",
                                                          "type":"Human with ants in his eyes",
                                                          "gender":"Male",
                                                          "origin":{
                                                             "name":"unknown",
                                                             "url":""
                                                          },
                                                          "location":{
                                                             "name":"Interdimensional Cable",
                                                             "url":"https://rickandmortyapi.com/api/location/6"
                                                          },
                                                          "image":"https://rickandmortyapi.com/api/character/avatar/20.jpeg",
                                                          "episode":[
                                                             "https://rickandmortyapi.com/api/episode/8"
                                                          ],
                                                          "url":"https://rickandmortyapi.com/api/character/20",
                                                          "created":"2017-11-04T22:34:53.659Z"
                                                       }
                                                    ]
                                                 }
                        """));

        mockMvc.perform(get("/api/randm/fill"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Peter Mustermann"))
        ;

    }

    @AfterAll
    static void afterAll() throws IOException {
        mockWebServer.shutdown();
    }

}
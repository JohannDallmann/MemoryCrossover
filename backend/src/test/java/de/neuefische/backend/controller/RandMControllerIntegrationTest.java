package de.neuefische.backend.controller;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
                .andExpect(jsonPath("$[0].name").value("Peter Mustermann"));

    }

    @Test
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
                .andExpect(jsonPath("$[0].name").value("Peter Mustermann"));

    }

    @Test
    void testGenerateBoardByCondition_case_no_parameters_delivers_twelve_characters() throws Exception {
        // given
        mockWebServer.enqueue(new MockResponse()
                .setHeader("Content-Type", "application/json")
                .setBody("""
                        {
                                                      "info": {
                                                          "count": 826,
                                                          "pages": 42,
                                                          "next": "https://rickandmortyapi.com/api/character?page=2",
                                                          "prev": null
                                                      },
                                                      "results": [
                                                          {
                                                              "id": 1,
                                                              "name": "Rick Sanchez",
                                                              "status": "Alive",
                                                              "species": "Humanx",
                                                              "type": "",
                                                              "gender": "Male",
                                                              "origin": {
                                                                  "name": "Earth (C-137)",
                                                                  "url": "https://rickandmortyapi.com/api/location/1"
                                                              },
                                                              "location": {
                                                                  "name": "Citadel of Ricks",
                                                                  "url": "https://rickandmortyapi.com/api/location/3"
                                                              },
                                                              "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                                              "url": "https://rickandmortyapi.com/api/character/1",
                                                              "created": "2017-11-04T18:48:46.250Z"
                                                          },
                                                          {
                                                              "id": 2,
                                                              "name": "Morty Smith",
                                                              "status": "Alive",
                                                              "species": "Human",
                                                              "type": "",
                                                              "gender": "Male",
                                                              "origin": {
                                                                  "name": "unknown",
                                                                  "url": ""
                                                              },
                                                              "location": {
                                                                  "name": "Citadel of Ricks",
                                                                  "url": "https://rickandmortyapi.com/api/location/3"
                                                              },
                                                              "image": "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                                                              "url": "https://rickandmortyapi.com/api/character/2",
                                                              "created": "2017-11-04T18:50:21.651Z"
                                                          },
                                                          {
                                                              "id": 3,
                                                              "name": "Summer Smith",
                                                              "status": "Alive",
                                                              "species": "Humanoid",
                                                              "type": "",
                                                              "gender": "Female",
                                                              "origin": {
                                                                  "name": "Earth (Replacement Dimension)",
                                                                  "url": "https://rickandmortyapi.com/api/location/20"
                                                              },
                                                              "location": {
                                                                  "name": "Earth (Replacement Dimension)",
                                                                  "url": "https://rickandmortyapi.com/api/location/20"
                                                              },
                                                              "image": "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
                                                              "url": "https://rickandmortyapi.com/api/character/3",
                                                              "created": "2017-11-04T19:09:56.428Z"
                                                          },
                                                          {
                                                              "id": 4,
                                                              "name": "Beth Smith",
                                                              "status": "Alive",
                                                              "species": "Humanoid",
                                                              "type": "",
                                                              "gender": "Female",
                                                              "origin": {
                                                                  "name": "Earth (Replacement Dimension)",
                                                                  "url": "https://rickandmortyapi.com/api/location/20"
                                                              },
                                                              "location": {
                                                                  "name": "Earth (Replacement Dimension)",
                                                                  "url": "https://rickandmortyapi.com/api/location/20"
                                                              },
                                                              "image": "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
                                                              "url": "https://rickandmortyapi.com/api/character/4",
                                                              "created": "2017-11-04T19:22:43.665Z"
                                                          },
                                                          {
                                                              "id": 5,
                                                              "name": "Jerry Smith",
                                                              "status": "Alive",
                                                              "species": "Mythological Creature",
                                                              "type": "",
                                                              "gender": "Male",
                                                              "origin": {
                                                                  "name": "Earth (Replacement Dimension)",
                                                                  "url": "https://rickandmortyapi.com/api/location/20"
                                                              },
                                                              "location": {
                                                                  "name": "Earth (Replacement Dimension)",
                                                                  "url": "https://rickandmortyapi.com/api/location/20"
                                                              },
                                                              "image": "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
                                                              "url": "https://rickandmortyapi.com/api/character/5",
                                                              "created": "2017-11-04T19:26:56.301Z"
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
                                                          "id":21,
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
                                                       }
                                                    ]
                                                 }
                        """));

        // when & then
        mockMvc.perform(get("/api/randm/fill"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/randm/game/board/generate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(12));
    }

    @Test
    void testGenerateBoardByCondition_case_parameters_for_quantity_determine_number_of_delivered_characters() throws Exception {
        // given
        mockWebServer.enqueue(new MockResponse()
                .setHeader("Content-Type", "application/json")
                .setBody("""
                        {
                                                      "info": {
                                                          "count": 826,
                                                          "pages": 42,
                                                          "next": "https://rickandmortyapi.com/api/character?page=2",
                                                          "prev": null
                                                      },
                                                      "results": [
                                                          {
                                                              "id": 1,
                                                              "name": "Rick Sanchez",
                                                              "status": "Alive",
                                                              "species": "Humanx",
                                                              "type": "",
                                                              "gender": "Male",
                                                              "origin": {
                                                                  "name": "Earth (C-137)",
                                                                  "url": "https://rickandmortyapi.com/api/location/1"
                                                              },
                                                              "location": {
                                                                  "name": "Citadel of Ricks",
                                                                  "url": "https://rickandmortyapi.com/api/location/3"
                                                              },
                                                              "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                                              "url": "https://rickandmortyapi.com/api/character/1",
                                                              "created": "2017-11-04T18:48:46.250Z"
                                                          },
                                                          {
                                                              "id": 2,
                                                              "name": "Morty Smith",
                                                              "status": "Alive",
                                                              "species": "Human",
                                                              "type": "",
                                                              "gender": "Male",
                                                              "origin": {
                                                                  "name": "unknown",
                                                                  "url": ""
                                                              },
                                                              "location": {
                                                                  "name": "Citadel of Ricks",
                                                                  "url": "https://rickandmortyapi.com/api/location/3"
                                                              },
                                                              "image": "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                                                              "url": "https://rickandmortyapi.com/api/character/2",
                                                              "created": "2017-11-04T18:50:21.651Z"
                                                          },
                                                          {
                                                              "id": 3,
                                                              "name": "Summer Smith",
                                                              "status": "Alive",
                                                              "species": "Humanoid",
                                                              "type": "",
                                                              "gender": "Female",
                                                              "origin": {
                                                                  "name": "Earth (Replacement Dimension)",
                                                                  "url": "https://rickandmortyapi.com/api/location/20"
                                                              },
                                                              "location": {
                                                                  "name": "Earth (Replacement Dimension)",
                                                                  "url": "https://rickandmortyapi.com/api/location/20"
                                                              },
                                                              "image": "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
                                                              "url": "https://rickandmortyapi.com/api/character/3",
                                                              "created": "2017-11-04T19:09:56.428Z"
                                                          },
                                                          {
                                                              "id": 4,
                                                              "name": "Beth Smith",
                                                              "status": "Alive",
                                                              "species": "Humanoid",
                                                              "type": "",
                                                              "gender": "Female",
                                                              "origin": {
                                                                  "name": "Earth (Replacement Dimension)",
                                                                  "url": "https://rickandmortyapi.com/api/location/20"
                                                              },
                                                              "location": {
                                                                  "name": "Earth (Replacement Dimension)",
                                                                  "url": "https://rickandmortyapi.com/api/location/20"
                                                              },
                                                              "image": "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
                                                              "url": "https://rickandmortyapi.com/api/character/4",
                                                              "created": "2017-11-04T19:22:43.665Z"
                                                          },
                                                          {
                                                              "id": 5,
                                                              "name": "Jerry Smith",
                                                              "status": "Alive",
                                                              "species": "Mythological Creature",
                                                              "type": "",
                                                              "gender": "Male",
                                                              "origin": {
                                                                  "name": "Earth (Replacement Dimension)",
                                                                  "url": "https://rickandmortyapi.com/api/location/20"
                                                              },
                                                              "location": {
                                                                  "name": "Earth (Replacement Dimension)",
                                                                  "url": "https://rickandmortyapi.com/api/location/20"
                                                              },
                                                              "image": "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
                                                              "url": "https://rickandmortyapi.com/api/character/5",
                                                              "created": "2017-11-04T19:26:56.301Z"
                                                          },
                                                          {
                                                              "id": 6,
                                                              "name": "Abadango Cluster Princess",
                                                              "status": "Alive",
                                                              "species": "Mythological Creature",
                                                              "type": "",
                                                              "gender": "Female",
                                                              "origin": {
                                                                  "name": "Abadango",
                                                                  "url": "https://rickandmortyapi.com/api/location/2"
                                                              },
                                                              "location": {
                                                                  "name": "Abadango",
                                                                  "url": "https://rickandmortyapi.com/api/location/2"
                                                              },
                                                              "image": "https://rickandmortyapi.com/api/character/avatar/6.jpeg",
                                                              "episode": [
                                                                  "https://rickandmortyapi.com/api/episode/27"
                                                              ],
                                                              "url": "https://rickandmortyapi.com/api/character/6",
                                                              "created": "2017-11-04T19:50:28.250Z"
                                                          },
                                                          {
                                                              "id": 7,
                                                              "name": "Abradolf Lincler",
                                                              "status": "unknown",
                                                              "species": "Animal",
                                                              "type": "Genetic experiment",
                                                              "gender": "Male",
                                                              "origin": {
                                                                  "name": "Earth (Replacement Dimension)",
                                                                  "url": "https://rickandmortyapi.com/api/location/20"
                                                              },
                                                              "location": {
                                                                  "name": "Testicle Monster Dimension",
                                                                  "url": "https://rickandmortyapi.com/api/location/21"
                                                              },
                                                              "image": "https://rickandmortyapi.com/api/character/avatar/7.jpeg",
                                                              "episode": [
                                                                  "https://rickandmortyapi.com/api/episode/10",
                                                                  "https://rickandmortyapi.com/api/episode/11"
                                                              ],
                                                              "url": "https://rickandmortyapi.com/api/character/7",
                                                              "created": "2017-11-04T19:59:20.523Z"
                                                          },
                                                          {
                                                              "id": 8,
                                                              "name": "Adjudicator Rick",
                                                              "status": "Dead",
                                                              "species": "Animal",
                                                              "type": "",
                                                              "gender": "Male",
                                                              "origin": {
                                                                  "name": "unknown",
                                                                  "url": ""
                                                              },
                                                              "location": {
                                                                  "name": "Citadel of Ricks",
                                                                  "url": "https://rickandmortyapi.com/api/location/3"
                                                              },
                                                              "image": "https://rickandmortyapi.com/api/character/avatar/8.jpeg",
                                                              "episode": [
                                                                  "https://rickandmortyapi.com/api/episode/28"
                                                              ],
                                                              "url": "https://rickandmortyapi.com/api/character/8",
                                                              "created": "2017-11-04T20:03:34.737Z"
                                                          },
                                                          {
                                                              "id": 9,
                                                              "name": "Agency Director",
                                                              "status": "Dead",
                                                              "species": "Robot",
                                                              "type": "",
                                                              "gender": "Male",
                                                              "origin": {
                                                                  "name": "Earth (Replacement Dimension)",
                                                                  "url": "https://rickandmortyapi.com/api/location/20"
                                                              },
                                                              "location": {
                                                                  "name": "Earth (Replacement Dimension)",
                                                                  "url": "https://rickandmortyapi.com/api/location/20"
                                                              },
                                                              "image": "https://rickandmortyapi.com/api/character/avatar/9.jpeg",
                                                              "episode": [
                                                                  "https://rickandmortyapi.com/api/episode/24"
                                                              ],
                                                              "url": "https://rickandmortyapi.com/api/character/9",
                                                              "created": "2017-11-04T20:06:54.976Z"
                                                          },
                                                          {
                                                              "id": 10,
                                                              "name": "Alan Rails",
                                                              "status": "Dead",
                                                              "species": "Robot",
                                                              "type": "Superhuman (Ghost trains summoner)",
                                                              "gender": "Male",
                                                              "origin": {
                                                                  "name": "unknown",
                                                                  "url": ""
                                                              },
                                                              "location": {
                                                                  "name": "Worldender's lair",
                                                                  "url": "https://rickandmortyapi.com/api/location/4"
                                                              },
                                                              "image": "https://rickandmortyapi.com/api/character/avatar/10.jpeg",
                                                              "episode": [
                                                                  "https://rickandmortyapi.com/api/episode/25"
                                                              ],
                                                              "url": "https://rickandmortyapi.com/api/character/10",
                                                              "created": "2017-11-04T20:19:09.017Z"
                                                          },
                                                          {
                                                              "id": 11,
                                                              "name": "Albert Einstein",
                                                              "status": "Dead",
                                                              "species": "Alien",
                                                              "type": "",
                                                              "gender": "Male",
                                                              "origin": {
                                                                  "name": "Earth (C-137)",
                                                                  "url": "https://rickandmortyapi.com/api/location/1"
                                                              },
                                                              "location": {
                                                                  "name": "Earth (Replacement Dimension)",
                                                                  "url": "https://rickandmortyapi.com/api/location/20"
                                                              },
                                                              "image": "https://rickandmortyapi.com/api/character/avatar/11.jpeg",
                                                              "episode": [
                                                                  "https://rickandmortyapi.com/api/episode/12"
                                                              ],
                                                              "url": "https://rickandmortyapi.com/api/character/11",
                                                              "created": "2017-11-04T20:20:20.965Z"
                                                          },
                                                          {
                                                                      "id": 12,
                                                                      "name": "Alexander",
                                                                      "status": "Dead",
                                                                      "species": "Alien",
                                                                      "type": "",
                                                                      "gender": "Male",
                                                                      "origin": {
                                                                          "name": "Earth (C-137)",
                                                                          "url": "https://rickandmortyapi.com/api/location/1"
                                                                      },
                                                                      "location": {
                                                                          "name": "Anatomy Park",
                                                                          "url": "https://rickandmortyapi.com/api/location/5"
                                                                      },
                                                                      "image": "https://rickandmortyapi.com/api/character/avatar/12.jpeg",
                                                                      "episode": [
                                                                          "https://rickandmortyapi.com/api/episode/3"
                                                                      ],
                                                                      "url": "https://rickandmortyapi.com/api/character/12",
                                                                      "created": "2017-11-04T20:32:33.144Z"
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
                                                          "id":21,
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
                                                       }
                                                    ]
                                                 }
                        """));

        // when

        mockMvc.perform(get("/api/randm/fill"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/randm/game/board/generate?m=2&n=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @AfterAll
    static void afterAll() throws IOException {
        mockWebServer.shutdown();
    }

}
package de.neuefische.backend.controller;

import lombok.RequiredArgsConstructor;
import de.neuefische.backend.model.RandMCharacter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import de.neuefische.backend.service.RandMService;

import java.util.List;

@RestController
@RequestMapping("/api/randm")
@RequiredArgsConstructor
public class RandMController {
    private final RandMService randMService;

    @GetMapping("/characters")
    public List<RandMCharacter> getAllCharacters(){
        return randMService.getAllCharacters();
    }

    @GetMapping("/fill")
    public List<RandMCharacter> fillCharactersFromApi(){
        return randMService.fillCharactersFromApi();
    }

//    TODO Change to POST Mapping
    @GetMapping("/game/board/generate")

    public List<RandMCharacter> generateBoardByCondition(@RequestParam int m, @RequestParam int n, @RequestParam int condition){
        return randMService.generateBoardByCondition(m,n,condition);
    }

}

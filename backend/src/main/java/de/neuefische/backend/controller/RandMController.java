package de.neuefische.backend.controller;

import de.neuefische.backend.model.BoardGenerationCondition;
import lombok.RequiredArgsConstructor;
import de.neuefische.backend.model.RandMCharacter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import de.neuefische.backend.service.RandMService;

import java.util.List;
import java.util.Objects;

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


    @GetMapping("/game/board/generate")

    public List<RandMCharacter> generateBoardByCondition(@RequestParam(required = false) Integer m,
                                                         @RequestParam(required = false) Integer n,
                                                         @RequestParam(required = false) BoardGenerationCondition condition) {

        return randMService.generateBoardByCondition(
                Objects.requireNonNullElse(m, 4),
                Objects.requireNonNullElse(n, 3),
                Objects.requireNonNullElse(condition, BoardGenerationCondition.DEFAULT));
    }

}

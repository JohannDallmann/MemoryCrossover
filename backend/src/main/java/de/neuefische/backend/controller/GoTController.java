package de.neuefische.backend.controller;

import de.neuefische.backend.model.GoTCharacter;
import de.neuefische.backend.service.GoTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/got")
@RequiredArgsConstructor
public class GoTController {
    private final GoTService goTService;

    @GetMapping("/characters")
    public List<GoTCharacter> getAllCharacters(){
        return goTService.getAllCharacters();
    }

    @GetMapping("/game/board/generate")
    public List<GoTCharacter> getRandomCharacterPairs(@RequestParam(required = false) Integer quantity) {
        int numberOfPairs = Objects.requireNonNullElse(quantity, 6); // Default: 6 pairs
        return goTService.getRandomCharacters(numberOfPairs);
    }

}

package de.neuefische.backend.controller;

import de.neuefische.backend.dto.CardDTO;
import de.neuefische.backend.service.MixedCardSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/mixed")
@RequiredArgsConstructor
public class MixedCardSetController {
    private final MixedCardSetService mixedCardSetService;
    @GetMapping("/game/board/generate")
    public List<CardDTO> getRandomCharacterPairs(@RequestParam(required = false) Integer quantity) {
        int numberOfPairs = Objects.requireNonNullElse(quantity, 6); // Default: 6 pairs
        return mixedCardSetService.getRandomCharacters(numberOfPairs);
    }
}

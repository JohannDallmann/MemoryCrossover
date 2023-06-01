package de.neuefische.backend.controller;

import de.neuefische.backend.model.GoTCharacter;
import de.neuefische.backend.service.GoTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/got")
@RequiredArgsConstructor
public class GoTController {
    private final GoTService goTService;

    @GetMapping("/characters")
    public List<GoTCharacter> getAllCharacters(){
        return goTService.getAllCharacters();
    }

}

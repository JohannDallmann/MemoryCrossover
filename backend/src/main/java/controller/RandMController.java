package controller;

import lombok.RequiredArgsConstructor;
import model.RandMCharacter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.RandMService;

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
    public void fillCharactersFromApi(){
        randMService.fillCharactersFromApi();
    }


}

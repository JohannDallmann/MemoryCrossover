package service;

import lombok.RequiredArgsConstructor;
import model.RandMCharacter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import repository.RandMRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RandMService {
    private final RandMRepo randMRepo;
    private GenerateUUIDService generateUUIDService;

    WebClient webClient = WebClient.create("https://rickandmortyapi.com/api/");

    public List<RandMCharacter> getAllCharacters() {
        return randMRepo.getAllCharacters();
    }


    public void fillCharactersFromApi() {
        List<RandMCharacter> randMCharacterList = new ArrayList<>();
        int page =1;
        int pageSize = 20;
        RandMRepo response;
        do {
            response = Objects.requireNonNull(webClient.get())
                    .uri("/character?page={page}&pageSize={pageSize}",page,pageSize)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .retrieve()
                    .toEntity(RandMRepo.class)
                    .block()
                    .getBody();
            randMCharacterList.addAll(response.getAllCharacters());
            page++;
        } while (response.getAllCharacters().size() == pageSize);

    }
}

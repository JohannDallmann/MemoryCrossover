package de.neuefische.backend.service;

import de.neuefische.backend.model.GoTCharacter;
import de.neuefische.backend.repository.GoTRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoTService {
    private final GoTRepo goTRepo;
    public List<GoTCharacter> getAllCharacters() {
            return goTRepo.findAll();
    }

}

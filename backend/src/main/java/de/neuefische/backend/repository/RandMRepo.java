package de.neuefische.backend.repository;

import de.neuefische.backend.model.RandMCharacter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface RandMRepo extends MongoRepository<RandMCharacter,String> {

}

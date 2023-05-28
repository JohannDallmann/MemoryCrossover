package de.neuefische.backend.repository;

import de.neuefische.backend.model.RandMCharacter;
import de.neuefische.backend.model.GroupBySpecies;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RandMRepo extends MongoRepository<RandMCharacter,String> {

        @org.springframework.data.mongodb.repository.Aggregation(pipeline = {
                         "{$group: {_id: '$species', characters: {$push: '$$ROOT'}}}"
        })

        List<GroupBySpecies> findRandomPairsBySpecies();
}

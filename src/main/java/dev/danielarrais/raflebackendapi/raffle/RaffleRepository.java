package dev.danielarrais.raflebackendapi.raffle;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RaffleRepository extends MongoRepository<RaffleDocument, String> {
}

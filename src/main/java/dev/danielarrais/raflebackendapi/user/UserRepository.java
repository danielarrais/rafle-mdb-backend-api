package dev.danielarrais.raflebackendapi.user;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserDocument, String> {
    Optional<UserDocument> findUserByPersonalID(String personalId);
}

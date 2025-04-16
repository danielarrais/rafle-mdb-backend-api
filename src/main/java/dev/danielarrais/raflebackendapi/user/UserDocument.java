package dev.danielarrais.raflebackendapi.user;

import dev.danielarrais.raflebackendapi.user.prize.Prize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users")
public class UserDocument {

    @Id
    private String id;

    @NotBlank(message = "The name is required")
    private String fullName;

    @NotBlank(message = "The personal ID is required")
    private String personalID;

    @Email(message = "The email is required")
    private String email;

    @NotBlank(message = "The phone number is required")
    private String phone;

    @NotBlank(message = "The city name is required")
    private String city;

    private Prize prize;
}

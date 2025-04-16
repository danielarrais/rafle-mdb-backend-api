package dev.danielarrais.raflebackendapi.raffle;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Document(collection = "prizes")
public class RaffleDocument {

    @Id
    private String id;
    private String name;

    @NotNull(message = "The difficult is required")
    private BigDecimal difficult;

    @NotNull(message = "The date is required")
    private LocalDate date;

    private List<RaffleItem> items;
}

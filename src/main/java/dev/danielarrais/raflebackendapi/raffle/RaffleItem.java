package dev.danielarrais.raflebackendapi.raffle;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RaffleItem {
    @NotBlank(message = "The name is required")
    private String name;

    @NotNull(message = "The type is required")
    private RaffleItemType type;

    @Min(value = 0, message = "The quantity must be greater than 0")
    private int quantity;
}

package dev.danielarrais.raflebackendapi.user.prize;

import dev.danielarrais.raflebackendapi.raffle.RaffleItemType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Prize {
    private String raffleId;
    private RaffleItemType type;
    private String name;
    private PrizeStatus status;
}

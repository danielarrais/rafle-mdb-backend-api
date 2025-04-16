package dev.danielarrais.raflebackendapi.api;

import dev.danielarrais.raflebackendapi.raffle.RaffleDocument;
import dev.danielarrais.raflebackendapi.raffle.RaffleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raffle")
public class RaffleApi {
    private final RaffleService raffleService;

    public RaffleApi(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    @PostMapping("/")
    public RaffleDocument raffle(@RequestBody RaffleDocument raffleDocument) {
        return raffleService.createRaffle(raffleDocument);
    }
}

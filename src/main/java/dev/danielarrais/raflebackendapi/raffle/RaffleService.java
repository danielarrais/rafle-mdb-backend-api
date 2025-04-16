package dev.danielarrais.raflebackendapi.raffle;

import dev.danielarrais.raflebackendapi.user.prize.Prize;
import dev.danielarrais.raflebackendapi.user.prize.PrizeStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import static java.math.BigDecimal.valueOf;

@Service
@RequiredArgsConstructor
public class RaffleService {
    private final RaffleRepository raffleRepository;

    public RaffleDocument createRaffle(RaffleDocument raffle) {
        return raffleRepository.save(raffle);
    }

    public Prize drawPrize(String raffleId) {
        var raffle = raffleRepository.findById(raffleId).orElseThrow();
        var difficult = raffle.getDifficult();
        var randomChance = Math.random();

        if (difficult.compareTo(new BigDecimal(randomChance)) < 0) {
            return null;
        }

        var itemsQuantity = raffle.getItems().size();

        if (itemsQuantity == 0) {
            return null;
        }

        var drownedItemPosition = valueOf(Math.random())
                .multiply(valueOf(itemsQuantity - 1))
                .setScale(0, RoundingMode.HALF_UP)
                .intValue();

        var raffleItem = raffle
                .getItems()
                .get(drownedItemPosition);

        decreaseItemQuantity(raffleItem);
        raffleRepository.save(raffle);

        return Prize.builder()
                .raffleId(raffleId)
                .name(raffleItem.getName())
                .type(raffleItem.getType())
                .status(PrizeStatus.PENDING)
                .build();
    }

    private void decreaseItemQuantity(RaffleItem raffleItem) {
        raffleItem.setQuantity(raffleItem.getQuantity() > 1 ? raffleItem.getQuantity() - 1 : 0);
    }
}

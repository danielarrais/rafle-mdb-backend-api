package dev.danielarrais.raflebackendapi.user;

import dev.danielarrais.raflebackendapi.raffle.RaffleService;
import dev.danielarrais.raflebackendapi.user.prize.Prize;
import dev.danielarrais.raflebackendapi.user.prize.PrizeStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RaffleService raffleService;

    public UserDocument create(UserDocument user, String raffleId) {
        var prize = raffleService.drawPrize(raffleId);

        user.setPrize(prize);

        return userRepository.save(user);
    }

    public Optional<UserDocument> getUser(String personalId) {
        return userRepository.findUserByPersonalID(personalId);
    }

    public Prize validatePrize(String personalId) {
        var user = userRepository.findUserByPersonalID(personalId).orElseThrow();
        var userPrize = user.getPrize();

        if (userPrize == null) {
            return null;
        }

        userPrize.setStatus(PrizeStatus.WITHDRAWN);
        userRepository.save(user);

        return user.getPrize();
    }
}

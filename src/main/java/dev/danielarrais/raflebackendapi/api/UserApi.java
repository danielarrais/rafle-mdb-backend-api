package dev.danielarrais.raflebackendapi.api;

import dev.danielarrais.raflebackendapi.user.UserDocument;
import dev.danielarrais.raflebackendapi.user.UserService;
import dev.danielarrais.raflebackendapi.user.prize.Prize;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @PostMapping("/draw/{raffleId}")
    public UserDocument draw(@RequestBody UserDocument user, @PathVariable String raffleId) {
        return userService.create(user, raffleId);
    }

    @PostMapping("/{personalId}")
    public Optional<UserDocument> getUser(@PathVariable String personalId) {
        return userService.getUser(personalId);
    }

    @PostMapping("/validate-prize/{personalId}")
    public Prize raffle(@PathVariable String personalId) {
        return userService.validatePrize(personalId);
    }
}

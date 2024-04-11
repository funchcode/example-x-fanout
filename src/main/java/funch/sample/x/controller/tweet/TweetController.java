package funch.sample.x.controller.tweet;

import funch.sample.x.service.tweet.WriteTweetUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/tweets")
@RestController
class TweetController {

    private final WriteTweetUseCase writeTweetUseCase;

    @PostMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerTweet(@PathVariable("userId") String userId) {
        writeTweetUseCase.registerTweet(userId);
    }

}

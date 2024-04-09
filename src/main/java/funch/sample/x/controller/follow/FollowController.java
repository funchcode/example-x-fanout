package funch.sample.x.controller.follow;

import funch.sample.x.controller.tweet.TweetResponse;
import funch.sample.x.service.follow.LoadFollowUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/follows")
@RestController
public class FollowController {
    private final LoadFollowUseCase loadFollowUseCase;

    @GetMapping("/feeds")
    @ResponseStatus(HttpStatus.OK)
    public List<TweetResponse> getLatestTweets(@RequestParam("userId") String userId) {
        return loadFollowUseCase.getLatestFollowTweets(userId)
                .stream().map(t -> new TweetResponse(t.getId()))
                .collect(Collectors.toList());
    }
}

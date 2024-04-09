package funch.sample.x.service.follow;

import funch.sample.x.repository.tweet.TweetRepository;
import funch.sample.x.service.tweet.TweetDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FollowService implements LoadFollowUseCase {

    private final static int LATEST_DAYS = 3;
    private final TweetRepository tweetRepository;

    @Override
    public List<TweetDto> getLatestFollowTweets(String userId) {
        return tweetRepository.getLatestFollowTweets(userId, LocalDateTime.now().minusDays(LATEST_DAYS));
    }

}

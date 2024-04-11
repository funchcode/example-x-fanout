package funch.sample.x.repository.tweet;

import funch.sample.x.XApplication;
import funch.sample.x.service.tweet.LoadTweetPort;
import funch.sample.x.service.tweet.TweetDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
class TweetPersistenceAdapter implements LoadTweetPort {

    private final TweetRepository tweetRepository;

    @Override
    public List<TweetDto> getLatestFollowTweets(String followeeId, LocalDateTime fewDayAgo) {
        XApplication.DB_CALL_COUNT.incrementAndGet();
        return tweetRepository.getLatestFollowTweets(followeeId, fewDayAgo);
    }

}
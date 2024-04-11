package funch.sample.x.repository.tweet;

import funch.sample.x.XApplication;
import funch.sample.x.service.tweet.LoadTweetPort;
import funch.sample.x.service.tweet.TweetDto;
import funch.sample.x.service.tweet.WriteTweetPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
class TweetPersistenceAdapter implements LoadTweetPort, WriteTweetPort {

    private final TweetRepository tweetRepository;

    @Override
    public List<TweetDto> getLatestFollowTweets(String followeeId, LocalDateTime fewDayAgo) {
        XApplication.DB_CALL_COUNT.incrementAndGet();
        return tweetRepository.getLatestFollowTweets(followeeId, fewDayAgo);
    }

    @Override
    public void registerTweet(String userId) {
        TweetEntity newTweet = new TweetEntity(UUID.randomUUID().toString(), userId, LocalDateTime.now(), userId);
        tweetRepository.save(newTweet);
    }

}
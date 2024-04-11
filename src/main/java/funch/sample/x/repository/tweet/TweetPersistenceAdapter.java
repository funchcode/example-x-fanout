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
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
class TweetPersistenceAdapter implements LoadTweetPort, WriteTweetPort {

    private final TweetRepository tweetRepository;
    private final UserFollowTweetsRedisRepository userFollowTweetsRedisRepository;

    @Override
    public List<TweetDto> getLatestFollowTweets(String followeeId, LocalDateTime fewDayAgo) {
        userFollowTweetsRedisRepository.findAll();
        XApplication.DB_CALL_COUNT.incrementAndGet();
        return tweetRepository.getLatestFollowTweets(followeeId, fewDayAgo);
    }

    @Override
    public void registerTweet(String userId, List<String> followerIds) {
        TweetEntity newTweet = new TweetEntity(UUID.randomUUID().toString(), userId, LocalDateTime.now(), userId);
        tweetRepository.save(newTweet);
        followerIds.forEach(followerId -> {
            UserFollowTweetsRedisHash userFollowTweetsRedisHash = userFollowTweetsRedisRepository.findById(followerId).orElse(null);
            if (userFollowTweetsRedisHash == null) {
                userFollowTweetsRedisHash = new UserFollowTweetsRedisHash(followerId);
            }
            userFollowTweetsRedisHash.addTweet(newTweet);
            userFollowTweetsRedisRepository.save(userFollowTweetsRedisHash);
        });
    }

}
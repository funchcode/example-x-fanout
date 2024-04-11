package funch.sample.x.repository.tweet;

import funch.sample.x.XApplication;
import funch.sample.x.service.tweet.LoadTweetPort;
import funch.sample.x.service.tweet.TweetDto;
import funch.sample.x.service.tweet.WriteTweetPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;
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
        UserFollowTweetsRedisHash userFollowTweetsRedisHash = userFollowTweetsRedisRepository.findById(followeeId).orElse(null);
        if (userFollowTweetsRedisHash == null) {
            XApplication.DB_CALL_COUNT.incrementAndGet();
            List<TweetDto> tweets = tweetRepository.getLatestFollowTweets(followeeId, fewDayAgo);
            userFollowTweetsRedisHash = new UserFollowTweetsRedisHash(followeeId);
            userFollowTweetsRedisHash.addTweet(tweets);
            userFollowTweetsRedisRepository.save(userFollowTweetsRedisHash);
            return tweets;
        } else {
            return userFollowTweetsRedisHash.getTweets();
        }
    }

    @Override
    public void registerTweet(String userId, List<String> followerIds) {
        long startNanoTime = System.nanoTime();
        log.info("[{} user] Time immediately before registration. start nano time -> {}", userId, startNanoTime);
        TweetEntity newTweet = new TweetEntity(UUID.randomUUID().toString(), userId, LocalDateTime.now(), userId);
        tweetRepository.save(newTweet);
        followerIds.forEach(followerId -> {
            UserFollowTweetsRedisHash userFollowTweetsRedisHash = userFollowTweetsRedisRepository.findById(followerId).orElse(null);
            if (userFollowTweetsRedisHash == null) {
                userFollowTweetsRedisHash = new UserFollowTweetsRedisHash(followerId);
            }
            userFollowTweetsRedisHash.addTweet(newTweet.toTweetDto());
            userFollowTweetsRedisRepository.save(userFollowTweetsRedisHash);
        });
        long endNanoTime = System.nanoTime();
        log.info("[{} user] Time immediately after registration. start nano time -> {}, end nano time -> {}, running time -> {}", userId, startNanoTime, endNanoTime, endNanoTime - startNanoTime);
    }

}
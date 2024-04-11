package funch.sample.x.repository.tweet;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@Getter
@RedisHash("user_follow_tweets")
public class UserFollowTweetsRedisHash {

    @Id
    private String userId;
    private List<Tweet> tweets = new ArrayList<>();

    public UserFollowTweetsRedisHash(String userId) {
        this.userId = userId;
    }

    UserFollowTweetsRedisHash addTweet(TweetEntity tweet) {
        this.tweets.add(new Tweet(tweet.getId(), tweet.getUsername(), tweet.getTimestamp().toString()));
        return this;
    }

    UserFollowTweetsRedisHash addTweet(List<TweetEntity> tweets) {
        this.tweets.addAll(tweets.stream().map(t -> new Tweet(t.getId(), t.getUsername(), t.getTimestamp().toString())).toList());
        return this;
    }

    static class Tweet {
        private final String id;
        private final String username;
        private final String timestamp;

        public Tweet(String id, String username, String timestamp) {
            this.id = id;
            this.username = username;
            this.timestamp = timestamp;
        }
    }

}

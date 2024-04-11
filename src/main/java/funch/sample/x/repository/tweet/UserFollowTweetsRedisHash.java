package funch.sample.x.repository.tweet;

import funch.sample.x.service.tweet.TweetDto;
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
    private List<TweetDto> tweets = new ArrayList<>();

    public UserFollowTweetsRedisHash(String userId) {
        this.userId = userId;
    }

    UserFollowTweetsRedisHash addTweet(TweetDto tweet) {
        this.tweets.add(tweet);
        return this;
    }

    UserFollowTweetsRedisHash addTweet(List<TweetDto> tweets) {
        this.tweets.addAll(tweets);
        return this;
    }

}

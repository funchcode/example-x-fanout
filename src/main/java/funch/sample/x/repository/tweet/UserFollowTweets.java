package funch.sample.x.repository.tweet;

import funch.sample.x.service.tweet.TweetDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class UserFollowTweets {

    private String userId;
    private List<TweetDto> tweets = new ArrayList<>();

    public UserFollowTweets(String userId, List<TweetDto> tweets) {
        this.userId = userId;
        this.tweets = tweets;
    }

}

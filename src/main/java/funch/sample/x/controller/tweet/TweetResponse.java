package funch.sample.x.controller.tweet;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TweetResponse {
    private final String tweetId;

    public TweetResponse(String tweetId) {
        this.tweetId = tweetId;
    }
}

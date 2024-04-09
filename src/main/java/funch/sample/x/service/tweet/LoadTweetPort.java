package funch.sample.x.service.tweet;

import java.util.List;

public interface LoadTweetPort {

    /**
     * 트윗 정보를 가져온다.
     */
    List<TweetDto> getTweets(String userId, int fewDayAgo);

}

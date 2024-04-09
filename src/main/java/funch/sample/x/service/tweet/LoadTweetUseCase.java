package funch.sample.x.service.tweet;

import java.util.List;

public interface LoadTweetUseCase {

    /**
     * 최근 트윗을 조회한다.
     */
    List<TweetDto> getLatestTweets(String userId);

}

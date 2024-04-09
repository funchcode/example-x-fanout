package funch.sample.x.service.follow;

import funch.sample.x.service.tweet.TweetDto;

import java.util.List;

public interface LoadFollowUseCase {

    /**
     * 팔로우한 사용자의 최근 트윗을 조회한다.
     */
    List<TweetDto> getLatestFollowTweets(String userId);

}

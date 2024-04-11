package funch.sample.x.service.tweet;

import java.time.LocalDateTime;
import java.util.List;

public interface LoadTweetPort {

    List<TweetDto> getLatestFollowTweets(String followeeId, LocalDateTime fewDayAgo);

}

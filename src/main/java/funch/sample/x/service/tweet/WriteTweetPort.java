package funch.sample.x.service.tweet;

import java.util.List;

public interface WriteTweetPort {

    void registerTweet(String userId, List<String> followerIds);

}

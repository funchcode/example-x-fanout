package funch.sample.x.service.tweet;

import java.util.Set;

public interface WriteTweetPort {

    void registerTweet(String userId, Set<String> followerIds);

}

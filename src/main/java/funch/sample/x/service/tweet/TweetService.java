package funch.sample.x.service.tweet;

import funch.sample.x.service.follow.FollowDto;
import funch.sample.x.service.follow.LoadFollowPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
class TweetService implements LoadTweetUseCase, WriteTweetUseCase {

    private final LoadFollowPort loadFollowPort;
    private final WriteTweetPort writeTweetPort;

    @Override
    public void registerTweet(String userId) {
        List<FollowDto> followers = loadFollowPort.getFollowers(userId);
        writeTweetPort.registerTweet(userId, followers.stream().map(FollowDto::getFollowerId).collect(Collectors.toList()));
    }

}

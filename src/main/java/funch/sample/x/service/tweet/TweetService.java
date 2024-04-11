package funch.sample.x.service.tweet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
class TweetService implements LoadTweetUseCase, WriteTweetUseCase {

    private final WriteTweetPort writeTweetPort;

    @Override
    public void registerTweet(String userId) {
        writeTweetPort.registerTweet(userId);
    }

}

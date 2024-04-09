package funch.sample.x.service.tweet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
class TweetService implements LoadTweetUseCase {

    private final static int LATEST_DAYS = 3;
    private final LoadTweetPort loadTweetPort;

    @Override
    public List<TweetDto> getLatestTweets(String userId) {
        return loadTweetPort.getTweets(userId, LATEST_DAYS);
    }

}

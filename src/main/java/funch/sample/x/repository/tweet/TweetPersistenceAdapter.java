package funch.sample.x.repository.tweet;

import funch.sample.x.service.tweet.LoadTweetPort;
import funch.sample.x.service.tweet.TweetDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
class TweetPersistenceAdapter implements LoadTweetPort {

    private final TweetRepository tweetRepository;

    @Override
    public List<TweetDto> getTweets(String userId, int fewDayAgo) {
        return tweetRepository.findAllByUser_IdAndTimestampAfter(userId, LocalDateTime.now().minusDays(fewDayAgo))
                .stream().map(TweetEntity::toTweetDto)
                .collect(Collectors.toList());
    }

}

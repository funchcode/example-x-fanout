package funch.sample.x.repository.follow;

import funch.sample.x.repository.tweet.TweetEntity;
import funch.sample.x.repository.tweet.TweetRepository;
import funch.sample.x.service.follow.FollowDto;
import funch.sample.x.service.follow.LoadFollowPort;
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
class FollowPersistenceAdapter implements LoadFollowPort {

    private final FollowRepository followRepository;

    @Override
    public List<FollowDto> getFollowings(String followeeId) {
        return followRepository.findAllById_FolloweeId(followeeId)
                .stream().map(FollowEntity::toFollowDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FollowDto> getFollowers(String followeeId) {
        return followRepository.findAllById_FollowerId(followeeId);
    }

}

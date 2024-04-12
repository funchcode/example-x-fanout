package funch.sample.x.repository.tweet;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserFollowTweetsRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public List<UserFollowTweets> findUserFollowTweets() {
        return null;
    }

    public List<UserFollowTweets> findAllUserFollowTweets(Set<String> followerIds) {
        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            followerIds.stream().spliterator().forEachRemaining(followerId -> connection.hashCommands().hGetAll(followerId.getBytes(StandardCharsets.UTF_8)));
            return null;
        });
        return null;
    }

}

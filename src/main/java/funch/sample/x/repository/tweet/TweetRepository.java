package funch.sample.x.repository.tweet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TweetRepository extends JpaRepository<TweetEntity, String> {

    List<TweetEntity> findAllByUser_IdAndTimestampAfter(String userId, LocalDateTime after);

}

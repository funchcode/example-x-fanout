package funch.sample.x.repository.tweet;

import funch.sample.x.service.tweet.TweetDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TweetRepository extends JpaRepository<TweetEntity, String> {

    /**
     select * from tweet t where t.userId in (select followerId from follow f where f.followeeId = userId) and t.timestamp > after order by t.timestamp desc
     */
    @Query("SELECT " +
            "new funch.sample.x.service.tweet.TweetDto(t.id, t.username, t.timestamp) " +
            "FROM TweetEntity t " +
            "WHERE t.user.id IN (" +
            "   SELECT f.id.follower.id " +
            "   FROM FollowEntity f " +
            "   WHERE f.id.followee.id = :followeeId" +
            ") " +
            "AND t.timestamp >= :fewDayAgo")
    List<TweetDto> getLatestFollowTweets(String followeeId, LocalDateTime fewDayAgo);

}

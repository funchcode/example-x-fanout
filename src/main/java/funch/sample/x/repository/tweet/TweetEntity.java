package funch.sample.x.repository.tweet;

import funch.sample.x.repository.user.UserEntity;
import funch.sample.x.service.tweet.TweetDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tweet")
public class TweetEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name", nullable = false)
    private String username;
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public TweetDto toTweetDto() {
        return new TweetDto(this.id, this.username, this.timestamp);
    }
}

package funch.sample.x.service.tweet;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class TweetDto {
    private final String id;
    private final String username;
    private final LocalDateTime timestamp;

    public TweetDto(String id, String username, LocalDateTime timestamp) {
        this.id = id;
        this.username = username;
        this.timestamp = timestamp;
    }

}

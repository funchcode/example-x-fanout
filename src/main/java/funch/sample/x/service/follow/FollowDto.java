package funch.sample.x.service.follow;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FollowDto {
    private final String followeeId;
    private final String followerId;

    public FollowDto(String followeeId, String followerId) {
        this.followeeId = followeeId;
        this.followerId = followerId;
    }
}

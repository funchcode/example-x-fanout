package funch.sample.x.repository.follow;

import funch.sample.x.service.follow.FollowDto;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "follow")
public final class FollowEntity {
    @EmbeddedId
    private FollowId id;

    public FollowDto toFollowDto() {
        return new FollowDto(this.id.getFollowee().getId(), this.id.getFollower().getId());
    }
}

package funch.sample.x.repository.follow;

import funch.sample.x.service.follow.FollowDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface FollowRepository extends JpaRepository<FollowEntity, String> {

    List<FollowEntity> findAllById_FolloweeId(String followeeId);
    @Query("SELECT " +
            "new funch.sample.x.service.follow.FollowDto(f.id.followee.id, f.id.follower.id) " +
            "FROM FollowEntity f " +
            "WHERE f.id.follower.id = :followeeId")
    List<FollowDto> findAllById_FollowerId(String followeeId);

}

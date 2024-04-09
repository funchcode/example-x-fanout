package funch.sample.x.repository.follow;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface FollowRepository extends JpaRepository<FollowEntity, String> {

    List<FollowEntity> findAllById_FolloweeId(String followeeId);

}

package funch.sample.x.service.follow;

import java.util.List;

public interface LoadFollowPort {

    /**
     * 내가 팔로우한 사람을 조회한다.
     */
    List<FollowDto> getFollowings(String followeeId);

    /**
     * 나를 팔로우하는 사람을 조회한다.
     */
    List<FollowDto> getFollowers(String followeeId);

}

package funch.sample.x.repository.tweet;

import org.springframework.data.repository.CrudRepository;

interface UserFollowTweetsRedisRepository extends CrudRepository<UserFollowTweetsRedisHash, String> {
}

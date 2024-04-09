package funch.sample.x.repository.tweet;

import funch.sample.x.service.tweet.LoadTweetPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
class TweetPersistenceAdapter implements LoadTweetPort {

    public static void main(String[] args) {
        for (int i = 2 ; i <= 10 ; i++) {
            int digit = String.valueOf(i).length();
            // 0_000_000_000
            StringBuilder numPrefix = new StringBuilder();
            for (int j = 0 ; j <= 10 - digit ; j++) {
                numPrefix.append("0");
            }
            numPrefix.append(i);
            System.out.printf(
                    "INSERT INTO user (id, name) VALUES ('USER%s', '유저%s');%n", numPrefix, numPrefix
            );
        }

        for (int i = 2 ; i <= 10 ; i++) {
            int digit = String.valueOf(i).length();
            // 0_000_000_000
            StringBuilder numPrefix = new StringBuilder();
            for (int j = 0 ; j <= 10 - digit ; j++) {
                numPrefix.append("0");
            }
            numPrefix.append(i);
            System.out.printf(
                    "INSERT INTO follow (followee_id, follower_id) VALUES ('USER0000000001', 'USER%s');%n", numPrefix
            );
        }

        for (int i = 1 ; i <= 1000 ; i++) {
            int digit = String.valueOf(i).length();
            // 0_000_000_000
            StringBuilder numPrefix = new StringBuilder("TWEET");
            for (int j = 0 ; j <= (5 - digit) ; j++) {
                numPrefix.append("0");
            }
            numPrefix.append(i);
            System.out.printf(
                    "INSERT INTO tweet (id, timestamp, name, user_id) VALUES ('%sUSER00000000002', NOW(), '?', 'USER00000000002');%n", numPrefix
            );
        }
    }

}
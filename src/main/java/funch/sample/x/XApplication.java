package funch.sample.x;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class XApplication {

    public static final AtomicInteger DB_CALL_COUNT = new AtomicInteger(0);

    public static void main(String[] args) {
        SpringApplication.run(XApplication.class, args);
    }

}

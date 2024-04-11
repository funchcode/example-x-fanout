package funch.sample.x.controller.system;

import funch.sample.x.XApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/system")
@RestController
public class SystemController {

    @GetMapping("/db-call")
    @ResponseStatus(HttpStatus.OK)
    public int getDbCallCount() {
        return XApplication.DB_CALL_COUNT.get();
    }

}

package junsulime.cloud.openfeign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignTestController {

    private final SelfCaller selfCaller;

    public FeignTestController(SelfCaller selfCaller) {
        this.selfCaller = selfCaller;
    }

    @GetMapping(path = "/test-feign")
    public String testFeign() {
        return selfCaller.getProjectName();
    }
}

package junsulime.cloud.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "selfCaller", url = "http://localhost:8080", configuration = SelfCallerConfiguration.class)
public interface SelfCaller {

    @GetMapping("/project-name")
    String getProjectName();
}

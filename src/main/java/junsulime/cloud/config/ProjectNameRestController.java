package junsulime.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ProjectNameRestController {

    private final String projectName;

    public ProjectNameRestController(@Value("${configuration.projectName}") String projectName) {
        System.out.println("ProjectNameRestController constructed");
        this.projectName = projectName;
    }

    @GetMapping("/project-name")
    public String getProjectName() {
        return projectName;
    }
}

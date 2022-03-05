package junsulime.cloud.introduce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class IntroduceController implements EnvironmentAware {

    private static final Logger log = LoggerFactory.getLogger(IntroduceController.class);

    private final String projectName;

    private final String projectDescription;

    public IntroduceController(@Value("${junsulime.cloud.project.name}") String projectName,
                               @Value("${junsulime.cloud.project.description}") String projectDescription) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
    }

    @Override
    public void setEnvironment(Environment environment) {
        log.info(environment.getProperty("junsulime.cloud.project.name"));
        log.info(environment.getProperty("junsulime.cloud.project.description"));
    }

    @GetMapping("/project-name")
    public String getProjectName() {
        return projectName;
    }
}

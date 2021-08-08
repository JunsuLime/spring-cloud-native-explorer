package junsulime.cloud;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@PropertySource("some.properties")
@Configuration
public class CloudApplication implements EnvironmentAware {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(CloudApplication.class);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${configuration.projectName}")
    private String fieldName;

    public CloudApplication(@Value("${configuration.projectName}") String fieldName) {
        System.out.println("constructor: " + fieldName);
    }

    @Value("${configuration.projectName}")
    public void setProjectName(String fieldName) {
        System.out.println("setter: " + fieldName);
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("environment: " + environment.getProperty("configuration.projectName"));
    }

    @Bean
    InitializingBean both(Environment environment, @Value("${configuration.projectName}") String projectName) {
        return () -> {
            System.out.println("initializingBean(env): " + environment.getProperty("configuration.projectName"));
            System.out.println("initializingBean(property): " + projectName);
        };
    }

    @PostConstruct
    public void onInit() {
        System.out.println("fieldName: " + fieldName);
    }

}

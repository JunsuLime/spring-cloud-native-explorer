package junsulime.cloud;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

@Configuration
public class CloudApplication implements EnvironmentAware {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.register(CloudApplication.class);
        context.refresh();
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

    @Configuration
    @Profile("prod")
    @PropertySource("some-prod.properties")
    public static class ProdConfiguration implements InitializingBean {

        @Override
        public void afterPropertiesSet() throws Exception {
            System.out.println("ProdConfiguration initialize");
        }
    }

    @Configuration
    @Profile({"default", "dev"})
    @PropertySource("some.properties")
    public static class DefaultConfiguration implements InitializingBean {

        @Override
        public void afterPropertiesSet() throws Exception {
            System.out.println("DefaultConfiguration initialize");
        }
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
    InitializingBean which(Environment environment, @Value("${configuration.projectName}") String projectName) {
        return () -> {
            System.out.println("activeProfiles: " + StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles()));
            System.out.println("initializingBean(env): " + environment.getProperty("configuration.projectName"));
            System.out.println("initializingBean(property): " + projectName);
        };
    }

    @PostConstruct
    public void onInit() {
        System.out.println("fieldName: " + fieldName);
    }

}

package junsulime.cloud.introduce;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Map;

public class IntroducePropertySourceLocator implements PropertySourceLocator {
    @Override
    public PropertySource<?> locate(Environment environment) {
        Map<String, Object> propertyMap = Map.of(
                "junsulime.cloud.project.name", "fixedName",
                "junsulime.cloud.project.description", "worked as intened");
        return new MapPropertySource("fixedProperty", propertyMap);
    }
}

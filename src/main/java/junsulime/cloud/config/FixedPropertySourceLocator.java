package junsulime.cloud.config;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Map;

public class FixedPropertySourceLocator implements PropertySourceLocator {
    @Override
    public PropertySource<?> locate(Environment environment) {
        Map<String, Object> propertyMap = Map.of(
                "configuration.projectName", "fixedName",
                "junsulime.intention", "worked as intened");
        return new MapPropertySource("fixedProperty", propertyMap);
    }
}

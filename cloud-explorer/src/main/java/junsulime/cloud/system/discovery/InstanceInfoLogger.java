package junsulime.cloud.system.discovery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
public class InstanceInfoLogger implements ApplicationRunner {

    private static Logger log = LoggerFactory.getLogger(InstanceInfoLogger.class);

    private final DiscoveryClient discoveryClient;

    private final String serviceId;

    public InstanceInfoLogger(DiscoveryClient discoveryClient,
                              @Value("${spring.application.name}") String serviceId) {
        this.discoveryClient = discoveryClient;
        this.serviceId = serviceId;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        discoveryClient.getInstances(serviceId).forEach(this::logServiceInstance);
    }

    private void logServiceInstance(ServiceInstance serviceInstance) {
        String message = String.format("Host = %s, port = %s, service ID = %s",
                serviceInstance.getHost(),
                serviceInstance.getPort(),
                serviceInstance.getServiceId());
        log.info("Service instance information: {}", message);
    }
}

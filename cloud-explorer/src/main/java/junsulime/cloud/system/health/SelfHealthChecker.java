package junsulime.cloud.system.health;

import feign.*;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "selfHealthChecker", url = "http://localhost:8080", configuration = SelfHealthChecker.Config.class)
public interface SelfHealthChecker {

    @GetMapping("/actuator/health")
    Object amIOk();

    class Config {
        @Bean
        public ErrorDecoder errorDecoder() {
            return new ErrorDecoder() {
                @Override
                public Exception decode(String methodKey, Response response) {
                    final FeignException feignException = FeignException.errorStatus(methodKey, response);
                    if (isRetryable(feignException)) {
                        return new RetryableException(
                                feignException.status(),
                                feignException.getMessage(),
                                feignException.request().httpMethod(),
                                feignException,
                                null,
                                feignException.request()
                        );
                    }
                    return feignException;
                }

                private boolean isRetryable(FeignException exception) {
                    return exception instanceof FeignException.GatewayTimeout
                            || exception instanceof FeignException.BadGateway
                            || exception instanceof FeignException.ServiceUnavailable;
                }
            };
        }

        @Bean
        public Retryer retryer() {
            return new Retryer.Default(500, 1000, 3);
        }

        @Bean
        public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
            return new BasicAuthRequestInterceptor("user", "password");
        }

        @Bean
        public Logger.Level feignLoggerLevel() {
            return Logger.Level.BASIC;
        }
    }
}

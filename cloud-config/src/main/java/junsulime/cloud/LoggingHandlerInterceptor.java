package junsulime.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggingHandlerInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LoggingHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle method: {}, path: {}", request.getMethod(), request.getRequestURI());
        return true;
    }
}

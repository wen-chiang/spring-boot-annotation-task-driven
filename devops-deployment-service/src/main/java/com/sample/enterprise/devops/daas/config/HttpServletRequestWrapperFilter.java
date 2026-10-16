package com.sample.enterprise.devops.daas.config;

import com.sample.enterprise.devops.daas.util.Loggable;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;

import static com.sample.enterprise.devops.daas.constants.DeploymentConstants.*;

@Component
public class HttpServletRequestWrapperFilter extends OncePerRequestFilter implements Loggable {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(request) {
            @Override
            public String getContentType() {
                if (request.getRequestURI().endsWith(OPENSHIFT_CONFIG_ENDPOINT) || request.getRequestURI().endsWith(OPENSHIFT_CLIENT_CONFIG_ENDPOINT) || request.getRequestURI().endsWith(OPENSHIFT_MANAGE_SECRET_WITH_UPLOAD_ENDPOINT)) {
                    return request.getContentType();
                } else {
                    // add as default
                    return MediaType.APPLICATION_JSON;
                }
            }
        };
        chain.doFilter(wrapper, response);
    }

}

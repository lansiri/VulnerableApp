package org.sasanlabs.configuration;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/** Ensures password-reset links never disclose their token through an HTTP referrer. */
@Component
@Order(Ordered.LOWEST_PRECEDENCE - 100)
public class PasswordResetReferrerPolicyFilter extends OncePerRequestFilter {

    private static final String RESET_PAGE_PATH = "/password-reset/reset.html";
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (isResetPage(request)) {
            response.setHeader("Referrer-Policy", "no-referrer");
        }
        filterChain.doFilter(request, response);
    }

    private boolean isResetPage(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        if (requestUri == null || !requestUri.endsWith(RESET_PAGE_PATH)) {
            return false;
        }

        return true;
    }
}

package edu.miu.cs.cs544.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
//@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JwtFilter extends OncePerRequestFilter {

    private final JwtHelper jwtHelper;

    private final UserDetailsService userDetailsService;

    @Autowired
    CustomHttpContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            var token = authorizationHeader.substring(7);
            var isValidToken = jwtHelper.validateToken(token);

            if (isValidToken && SecurityContextHolder.getContext().getAuthentication() == null) {
                var userName = jwtHelper.getUserNameFromToken(token);
                context.setToken(token);
                var userDetails = userDetailsService.loadUserByUsername(userName);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                authentication.setDetails(userDetails);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }


}

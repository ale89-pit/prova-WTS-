package com.tecninf.commesse.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String role = request.getHeader("role");
        String username = request.getHeader("username");
        if (role != null) {
            List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(role));
            UsernamePasswordAuthenticationToken filter =
                    new UsernamePasswordAuthenticationToken(username, null,
                            grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(filter);
        }
        filterChain.doFilter(request, response);
    }
}

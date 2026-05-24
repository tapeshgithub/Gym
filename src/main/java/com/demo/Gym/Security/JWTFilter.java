package com.demo.Gym.Security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.Gym.services.CustomerUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    private final CustomerUserDetailsService customUserDetailsService;

    public JWTFilter(
            JWTUtil jwtUtil,
            CustomerUserDetailsService customUserDetailsService
    ) {

        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getServletPath();

        // VERY IMPORTANT
        // Skip JWT checking for register/login

        if (
                path.equals("/gym/auth/register")
                || path.equals("/gym/auth/login")
        ) {

            filterChain.doFilter(request, response);

            return;
        }

        final String authHeader =
                request.getHeader("Authorization");

        String email = null;

        String jwt = null;

        if (
                authHeader != null
                && authHeader.startsWith("Bearer ")
        ) {

            jwt = authHeader.substring(7);

            email = jwtUtil.extractEmail(jwt);
        }

        if (
                email != null
                && SecurityContextHolder
                .getContext()
                .getAuthentication() == null
        ) {

            UserDetails userDetails =
                    customUserDetailsService
                    .loadUserByUsername(email);

            if (
                    jwtUtil.validateToken(jwt, email)
            ) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                        .buildDetails(request)
                );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
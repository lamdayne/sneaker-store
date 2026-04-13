package com.poly.sneakerstore.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import com.poly.sneakerstore.dto.request.IntrospectRequest;
import com.poly.sneakerstore.service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

@Slf4j
@Component
@RequiredArgsConstructor
public class PreFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        String token = "";
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            token = bearerToken.substring(7);
        }

        if (token.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            var introspectResponse = authenticationService.introspect(IntrospectRequest.builder()
                            .token(token).build());

            if (!introspectResponse.isValid()) {
                throw new BadCredentialsException("Invalid token");
            }
            SignedJWT jwt = SignedJWT.parse(token);
            String email = jwt.getJWTClaimsSet().getSubject();

            var authentication = new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    new ArrayList<>()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (ParseException | JOSEException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new RuntimeException(e);
        }
        filterChain.doFilter(request, response);
    }
}

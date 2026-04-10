package com.poly.sneakerstore.config;

import com.poly.sneakerstore.dto.response.ApiResponse;
import com.poly.sneakerstore.dto.response.ResponseFailure;
import com.poly.sneakerstore.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;

        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();

        ObjectMapper mapper = new ObjectMapper();

        response.getWriter().print(mapper.writeValueAsString(apiResponse));
        response.flushBuffer();
    }
}

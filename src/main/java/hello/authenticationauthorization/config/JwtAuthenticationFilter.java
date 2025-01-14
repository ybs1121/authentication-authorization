package hello.authenticationauthorization.config;

import hello.authenticationauthorization.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization"); // 요청에 인증 헤더 가져오기

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Bearer token 이 있으면?
            String token = authorizationHeader.substring(7);
            log.info("token: {}", token);
            if (jwtUtil.validateToken(token)) {
                String nameChannel = jwtUtil.extractUsername(token);
                log.info("nameChannel: {}", nameChannel);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(nameChannel, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}

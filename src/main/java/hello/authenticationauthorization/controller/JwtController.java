package hello.authenticationauthorization.controller;

import hello.authenticationauthorization.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtController {

    private final JwtUtil jwtUtil;


    @GetMapping("/token/{name}")
    public String token(@PathVariable String name) {
        return jwtUtil.generateToken(name);
    }

    @GetMapping("/api/{name}")
    public String api(@PathVariable String name, HttpServletRequest request) {
        System.out.println("Jwt Api call");
        String authorization = request.getHeader("Authorization");
        System.out.println("authorization = " + authorization);
        return jwtUtil.extractUsername(request.getHeader("Authorization"));
    }
}

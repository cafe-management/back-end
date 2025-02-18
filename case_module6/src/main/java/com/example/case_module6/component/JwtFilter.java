//package com.example.case_module6.component;
//
//import io.jsonwebtoken.Jwts;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//@Component
//@WebFilter("/*")
//public class JwtFilter extends OncePerRequestFilter {
//    @Value("${jwt.secret}")
//    private String secretKey;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String token = authorizationHeader.substring(7); // Loại bỏ "Bearer "
//            try {
//                String username = Jwts.parser()
//                        .setSigningKey(secretKey)
//                        .parseClaimsJws(token)
//                        .getBody()
//                        .getSubject();
//
//                if (username != null) {
//                    Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            } catch (Exception e) {
//                System.out.println("Token không hợp lệ hoặc hết hạn.");
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}

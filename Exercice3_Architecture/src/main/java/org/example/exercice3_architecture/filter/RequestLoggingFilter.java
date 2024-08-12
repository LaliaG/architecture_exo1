package org.example.exercice3_architecture.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String httpMethod = request.getMethod();
        String clientIP = request.getRemoteAddr();
        LocalDateTime requestTime = LocalDateTime.now();


        System.out.println("Requête reçue :");
        System.out.println("URI : " + requestURI);
        System.out.println("Méthode HTTP : " + httpMethod);
        System.out.println("Timestamp : " + requestTime);
        System.out.println("Adresse IP du client : " + clientIP);
       /* String uri = request.getRequestURI();
        String method = request.getMethod();
        String clientIp = request.getRemoteAddr();
        Instant timestamp = Instant.now();

        System.out.println("Requête reçue :");
        System.out.println("URI : " + requestURI);
        System.out.println("Méthode HTTP : " + httpMethod);
        System.out.println("Timestamp : " + requestTime);
        System.out.println("Adresse IP du client : " + clientIP);

       // System.out.println("Requête reçue - URI: " + uri + ", Méthode: " + method + ", IP du client: " + clientIp + ", Timestamp: " + timestamp);
*/
        filterChain.doFilter(request, response);
    }
}

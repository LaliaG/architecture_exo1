package org.example.projectservice.infrastructure.integration;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EmployeeClient {

    private final RestTemplate restTemplate;

    public EmployeeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getEmployeeById(Long id) {
        return restTemplate.getForObject("http://localhost:8080/employees/" + id, String.class);
    }
}

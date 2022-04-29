package com.jupiteropt.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.jupiteropt.assessment.repository"})
public class JupiteroptApplication {

  public static void main(String[] args) {
    SpringApplication.run(JupiteroptApplication.class, args);
  }
}

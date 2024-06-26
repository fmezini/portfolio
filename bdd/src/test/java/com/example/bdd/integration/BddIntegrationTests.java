package com.example.bdd.integration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@CucumberOptions(
        features = "src/test/resources", // Diretório dos arquivos .feature
        glue = "com.example.bdd.integration", // Pacote onde estão as classes Steps Definitions
        plugin = {"pretty", "html:target/cucumber-reports"} // Configuração de relatórios
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BddIntegrationTests {

    @LocalServerPort
    protected int port;

}
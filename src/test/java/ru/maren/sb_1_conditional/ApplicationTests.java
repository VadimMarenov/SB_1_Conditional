package ru.maren.sb_1_conditional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final GenericContainer<?> devapp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    private static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);
    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    void contextLoadsDevApp() {
        String url = String.format("http://localhost:%d/profile", devapp.getMappedPort(8080));
        ResponseEntity<String> devEntity = restTemplate.getForEntity(url, String.class);
        String actual = devEntity.getBody();
        String expected = "Current profile is dev";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void contextLoadsProdApp() {
        String url = String.format("http://localhost:%d/profile", prodapp.getMappedPort(8081));
        ResponseEntity<String> prodEntity = restTemplate.getForEntity(url, String.class);
        String actual = prodEntity.getBody();
        String expected = "Current profile is production";

        Assertions.assertEquals(expected, actual);
    }

}

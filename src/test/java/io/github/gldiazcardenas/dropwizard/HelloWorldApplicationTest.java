package io.github.gldiazcardenas.dropwizard;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.github.gldiazcardenas.dropwizard.model.HelloWorld;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class HelloWorldApplicationTest {

    public static final DropwizardAppExtension<HelloWorldConfiguration> DROPWIZARD =
        new DropwizardAppExtension<>(HelloWorldApplication.class, "test-configuration.yml");

    @Test
    void testHelloWorld() {
        Client client = DROPWIZARD.client();

        Response response = client.target(String.format("http://localhost:%d/hello-world", DROPWIZARD.getLocalPort()))
            .request()
            .get();

        assertThat(response.getStatus()).isEqualTo(200);

        HelloWorld helloWorld = response.readEntity(HelloWorld.class);

        assertThat(helloWorld).isNotNull();
        assertThat(helloWorld.getId()).isGreaterThan(0);
        assertThat(helloWorld.getContent()).isNotBlank();
        assertThat(helloWorld.getContent()).contains("Hello, Tester!");
    }

}

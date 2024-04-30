package io.github.gldiazcardenas.dropwizard.resources;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import io.github.gldiazcardenas.dropwizard.api.HelloWorld;
import io.github.gldiazcardenas.dropwizard.services.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(DropwizardExtensionsSupport.class)
public class HelloWorldResourceTest {

    private static final HelloWorldService SERVICE = mock(HelloWorldService.class);
    private static final ResourceExtension EXT = ResourceExtension.builder()
        .addResource(new HelloWorldResource(SERVICE))
        .build();

    @Test
    public void testGreeting() {
        int id = 1;
        String name = "Developer";
        String content = "Hello, " + name + "!";
        when(SERVICE.greeting(anyString())).thenReturn(new HelloWorld(id, content));

        HelloWorld found = EXT.target("/hello-world")
            .queryParam("name", name)
            .request()
            .get(HelloWorld.class);

        assertThat(found.getId()).isEqualTo(id);
        assertThat(found.getContent()).isEqualTo(content);

        verify(SERVICE).greeting(eq(name));
    }

}

package io.github.gldiazcardenas.dropwizard.services;

import io.github.gldiazcardenas.dropwizard.HelloWorldConfiguration;
import io.github.gldiazcardenas.dropwizard.api.HelloWorld;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HelloWorldServiceTest {

    @Mock
    private HelloWorldConfiguration configuration;

    @InjectMocks
    private HelloWorldService helloWorldService;

    @Test
    public void testGreetings() {
        // Given
        String visitorName = "John";
        when(configuration.getGreetingTemplate()).thenReturn("Hello, %s!");
        when(configuration.getDefaultVisitorName()).thenReturn("Stranger");

        // When
        HelloWorld helloWorld = helloWorldService.greeting(visitorName);

        // Then
        assertEquals(1, helloWorld.getId());
        assertEquals("Hello, John!", helloWorld.getContent());
    }

}

package io.github.gldiazcardenas.dropwizard.services;

import io.github.gldiazcardenas.dropwizard.HelloWorldConfiguration;
import io.github.gldiazcardenas.dropwizard.api.HelloWorld;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
public class HelloWorldService {

    private final HelloWorldConfiguration configuration;
    private final AtomicLong visitsCounter;

    @Inject
    public HelloWorldService(HelloWorldConfiguration configuration) {
        this.configuration = configuration;
        this.visitsCounter = new AtomicLong();
    }

    public HelloWorld greeting(String visitorName) {
        final String greeting = String.format(configuration.getGreetingTemplate(),
            Optional.ofNullable(visitorName).orElse(configuration.getDefaultVisitorName()));
        return new HelloWorld(visitsCounter.incrementAndGet(), greeting);
    }

}

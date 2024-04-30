package io.github.gldiazcardenas.dropwizard.resources;

import io.github.gldiazcardenas.dropwizard.HelloWorldConfiguration;
import io.github.gldiazcardenas.dropwizard.model.HelloWorld;
import com.codahale.metrics.annotation.Timed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    private final String greetingTemplate;
    private final String defaultVisitorName;
    private final AtomicLong visitsCounter;

    @Inject
    public HelloWorldResource(HelloWorldConfiguration configuration) {
        this.greetingTemplate = configuration.getGreetingTemplate();
        this.defaultVisitorName = configuration.getDefaultVisitorName();
        this.visitsCounter = new AtomicLong();
    }

    @GET
    @Timed
    public HelloWorld greeting(@QueryParam("name") String name) {
        final String content = String.format(greetingTemplate, Optional.ofNullable(name).orElse(defaultVisitorName));
        return new HelloWorld(visitsCounter.incrementAndGet(), content);
    }

}

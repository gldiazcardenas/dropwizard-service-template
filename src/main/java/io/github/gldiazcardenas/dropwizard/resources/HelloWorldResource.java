package io.github.gldiazcardenas.dropwizard.resources;

import com.codahale.metrics.annotation.Timed;
import io.github.gldiazcardenas.dropwizard.api.HelloWorld;
import io.github.gldiazcardenas.dropwizard.services.HelloWorldService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    private final HelloWorldService service;

    @Inject
    public HelloWorldResource(HelloWorldService service) {
        this.service = service;
    }

    @GET
    @Timed
    public HelloWorld greeting(@QueryParam("name") String name) {
        return service.greeting(name);
    }

}

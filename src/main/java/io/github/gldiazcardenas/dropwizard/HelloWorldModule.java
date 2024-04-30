package io.github.gldiazcardenas.dropwizard;

import io.github.gldiazcardenas.dropwizard.resources.HelloWorldResource;
import com.google.inject.AbstractModule;
import jakarta.inject.Singleton;

public class HelloWorldModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(HelloWorldResource.class).in(Singleton.class);
    }

}

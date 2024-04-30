package io.github.gldiazcardenas.dropwizard;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.github.gldiazcardenas.dropwizard.health.HelloWorldHealthCheck;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // Uses the configuration file from resources folder
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());

        // Adds Guice DI support
        bootstrap.addBundle(GuiceBundle.builder().modules(new HelloWorldModule()).build());
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) {
        environment.healthChecks().register("hello-world", new HelloWorldHealthCheck(configuration.getGreetingTemplate()));
    }

}

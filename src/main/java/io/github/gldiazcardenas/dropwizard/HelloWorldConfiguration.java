package io.github.gldiazcardenas.dropwizard;

import io.dropwizard.core.Configuration;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloWorldConfiguration extends Configuration {

    @NotEmpty
    private String greetingTemplate;

    @NotEmpty
    private String defaultVisitorName = "Stranger";

}

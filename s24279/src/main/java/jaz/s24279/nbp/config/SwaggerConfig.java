package jaz.s24279.nbp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.setEmail("kolokwium@nbp.pl");
        contact.setName("s24279");
        contact.setUrl("https://www.nbp.pl");

        Info info = new Info()
                .title("NBPapi")
                .version("2.0.1")
                .contact(contact)
                .description("Some description");

        return new OpenAPI().info(info);
    }
}

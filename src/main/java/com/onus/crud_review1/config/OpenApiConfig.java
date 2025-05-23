package com.onus.crud_review1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class OpenApiConfig {
    @Value("${fiction2st.openapi.dev-url")
    private String devUrl;

    @Value("${fiction2st.openapi.prod-url")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Fiction2st's Dev Environment API");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        devServer.setDescription("Fiction2st's Prod Environment API");

        Contact contact = new Contact();
        contact.setEmail("fakerfan90@gmail.com");
        contact.setName("faker");
        contact.url("https://www.fiction2st.com");

        License license = new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");

        Info info = new Info()
                .title("Product Management API")
                .version("1.0")
                .contact(contact)
                .license(license)
                .termsOfService("https://www.fakerfan.com/terms")
                .license(license);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }

}

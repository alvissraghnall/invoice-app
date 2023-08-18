package com.alviss.invoice_app.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {
    
    @Value("${base-url}")
    private String devUrl;

    @Value("${prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI openApiConfig () {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in development environment");
    
        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in production environment");
    
        Contact contact = new Contact();
        contact.setEmail("isiomastewart@gmail.com");
        contact.setName("Alviss Raghnall");
        contact.setUrl("https://alvissraghnall.vercel.app");

        License mitLicense = new License().name("MIT").url("https://choosealicense.com/licenses/mit");

        Info info = new Info()
            .title("Invoice Application")
            .version("1.0")
            .contact(contact)
            .description("RESTful API with basic CRUD functionality that enables user manage invoices.")
            .termsOfService("")
            .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }

}


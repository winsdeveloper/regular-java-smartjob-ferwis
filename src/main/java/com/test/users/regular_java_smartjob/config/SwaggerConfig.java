package com.test.users.regular_java_smartjob.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;

import java.util.List;

@Configuration
public class SwaggerConfig {

  @Value("${springdoc.info.title}")
  private String title;

  @Value("${springdoc.info.version}")
  private String version;

  @Value("${springdoc.info.description}")
  private String description;

  @Value("${springdoc.info.contact.name}")
  private String contactName;

  @Value("${springdoc.info.contact.email}")
  private String contactEmail;

  @Value("${springdoc.info.contact.url}")
  private String contactUrl;

  @Value("${springdoc.info.license.name}")
  private String licenseName;

  @Value("${springdoc.info.license.url}")
  private String licenseUrl;

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .servers(List.of(
            new Server().url("http://localhost:8012").description("Servidor local"),
            new Server().url("https://api.regular-java-smartjob.com").description("Producción")
        ))
        .info(new io.swagger.v3.oas.models.info.Info()
            .title(title)
            .version(version)
            .description(description)
            .contact(new io.swagger.v3.oas.models.info.Contact()
                .name(contactName)
                .email(contactEmail)
                .url(contactUrl))
            .license(new io.swagger.v3.oas.models.info.License()
                .name(licenseName)
                .url(licenseUrl)));
  }


  @Bean
  ForwardedHeaderFilter forwardedHeaderFilter() {
    return new ForwardedHeaderFilter();
  }
}

package com.theslavahero.denevy.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors

import springfox.documentation.builders.RequestHandlerSelectors

import springfox.documentation.spi.DocumentationType

import springfox.documentation.spring.web.plugins.Docket


/**
 * Created by theslavahero on 28.04.22
 */
@Configuration
class SpringFoxConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
    }
}
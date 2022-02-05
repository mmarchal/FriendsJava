package com.maxfriends.back.config;

import com.maxfriends.back.dto.SortieDto;
import com.maxfriends.back.entity.Sortie;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ApplicationConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public ModelMapper model() {
        ModelMapper mapper = new ModelMapper();

        mapper.addMappings(new PropertyMap<SortieDto, Sortie>() {

            @Override
            protected void configure() {
            }
        });

        return mapper;
    }


}

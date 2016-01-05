package com.classkick.sbj.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Configuration
public class SerializationConfig {

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        List<MediaType> mediaTypes = Lists.newArrayList(converter.getSupportedMediaTypes());
        mediaTypes.add(MediaType.TEXT_PLAIN);

        converter.setSupportedMediaTypes(mediaTypes);

        return converter;
    }
}

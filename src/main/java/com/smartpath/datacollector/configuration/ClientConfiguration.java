package com.smartpath.datacollector.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ClientConfiguration {

    @Bean("RestTemplate")
    public RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        for (HttpMessageConverter<?> myConverter : restTemplate.getMessageConverters ()) {
            if (myConverter instanceof MappingJackson2HttpMessageConverter) {
                List<MediaType> myMediaTypes = new ArrayList<>();
                myMediaTypes.addAll (myConverter.getSupportedMediaTypes ());
                myMediaTypes.add (MediaType.parseMediaType ("text/javascript; charset=utf-8"));
                ((MappingJackson2HttpMessageConverter) myConverter).setSupportedMediaTypes (myMediaTypes);
            }
        }
        return restTemplate;
    }
}

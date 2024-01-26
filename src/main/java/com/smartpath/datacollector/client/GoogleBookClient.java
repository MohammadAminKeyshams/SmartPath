package com.smartpath.datacollector.client;

import com.smartpath.datacollector.exception.ProviderNotRespondException;
import com.smartpath.datacollector.model.GeneralResponseDTO;
import com.smartpath.datacollector.model.google.GoogleResponseDTO;
import com.smartpath.datacollector.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class GoogleBookClient implements ProviderClient {
    private final RestTemplate restTemplate;

    @Value("${data-collector.base-url.google-book}")
    private String baseUrl;
    @Value("${data-collector.limit.google-book}")
    private String limit;

    @Value("${data-collector.api-key.google-book}")
    private String apiKey;
    public GeneralResponseDTO fetch(String searchWord){
        String fullUrl = new StringBuffer(baseUrl).append(searchWord).append("&maxResults=").append(limit).append("&key=").append(apiKey).toString();
        try {
            ResponseEntity<GoogleResponseDTO> googleResponse = restTemplate.exchange(fullUrl, HttpMethod.GET, null, GoogleResponseDTO.class);
            return Mapper.mapGoogleResponseToGeneralResponse(googleResponse);

        }catch (Exception e){
            log.error("{} provider not responding because : {}",this.getClass().getSimpleName(),e.getMessage());
            throw new ProviderNotRespondException();
        }
    }

}

package com.smartpath.datacollector.client;

import com.smartpath.datacollector.exception.ProviderNotRespondException;
import com.smartpath.datacollector.model.GeneralResponseDTO;
import com.smartpath.datacollector.model.itunes.ItunesResponseDTO;
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
public class ItunesClient implements ProviderClient {
    private final RestTemplate restTemplate;
    @Value("${data-collector.base-url.itunes}")
    private String baseUrl;
    @Value("${data-collector.limit.itunes}")
    private String limit;
    public GeneralResponseDTO fetch(String searchWord){
        String fullUrl = new StringBuffer(baseUrl).append(searchWord).append("&limit=").append(limit).toString();
        try {
            ResponseEntity<ItunesResponseDTO> itunesResponse = restTemplate.exchange(fullUrl, HttpMethod.GET, null, ItunesResponseDTO.class);
            return Mapper.mapItunesResponseToGeneralResponse(itunesResponse);
        }catch (Exception e){
            log.error("{} provider not responding because : {}",this.getClass().getSimpleName(),e.getMessage());
            throw new ProviderNotRespondException();
        }
    }
}

package com.smartpath.datacollector.controller;

import com.smartpath.datacollector.model.GeneralResponseDTO;
import com.smartpath.datacollector.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/v1/info")
@RequiredArgsConstructor
public class InfoController {
    private final InfoService service;

    private final RestTemplate restTemplate;

    @GetMapping("/get")
    public ResponseEntity<List<GeneralResponseDTO>> getInfo(@RequestParam String find, @RequestParam String type) throws InterruptedException {
        return new ResponseEntity<>(service.getInfo(find,type), HttpStatus.OK);
    }
/*    @GetMapping("/haha")
    public GoogleResponseDTO haha(@RequestParam String find, @RequestParam String type) throws InterruptedException {

        ResponseEntity<GoogleResponseDTO> exchange = restTemplate.exchange("https://www.googleapis.com/books/v1/volumes?q=sherlooks&maxResults=1&totalItems=1&key=AIzaSyCtyoOJ4CL86WRqh6vfHeMT0oRacI-OaZY",
                HttpMethod.GET, null, GoogleResponseDTO.class);
        return exchange.getBody();
    }*/
}

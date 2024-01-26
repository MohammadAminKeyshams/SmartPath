package com.smartpath.datacollector.service;

import com.smartpath.datacollector.client.ProviderClient;
import com.smartpath.datacollector.exception.ProviderNotFoundException;
import com.smartpath.datacollector.model.GeneralResponseDTO;
import com.smartpath.datacollector.model.Media;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InfoServiceImpl implements InfoService {
    private final ClientSelector selector;
    @Override
    public List<GeneralResponseDTO> getInfo(String searchWord, String inputType) {
        List<GeneralResponseDTO> response =new ArrayList<>();;
        if (inputType.equals(Media.ALL)){
            List<Callable<GeneralResponseDTO>> callableList = new ArrayList<>();
            for (String type : Media.ALL_TYPE) {
                callableList.add(()-> selector.select(type).fetch(searchWord));
            }
            fetchParallel(callableList, response);
        }else {
            response.add(selector.select(inputType).fetch(searchWord));
        }
        return response;
    }
    private void fetchParallel(List<Callable<GeneralResponseDTO>> callableList, List<GeneralResponseDTO> response){
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            executorService.invokeAll(callableList).forEach(responseDTOFuture -> {
                try {
                    response.add(responseDTOFuture.get());
                } catch (InterruptedException | ExecutionException e) {
                    log.error("Cant get future from executor ");
                }
            });
        } catch (InterruptedException e) {
            log.error("One or more service is not available ");
        }
    }


    @Component
    @RequiredArgsConstructor
    public static class ClientSelector {
        private final List<ProviderClient> clients;
        public ProviderClient select(String type){
            switch (type){
                case Media.BOOK -> { return clients.get(0); }
                case Media.MUSIC -> { return clients.get(1); }
                default -> throw new ProviderNotFoundException();
            }
        }
    }
}

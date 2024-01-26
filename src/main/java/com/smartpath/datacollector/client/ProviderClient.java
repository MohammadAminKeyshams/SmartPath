package com.smartpath.datacollector.client;


import com.smartpath.datacollector.model.GeneralResponseDTO;


public interface ProviderClient {
    GeneralResponseDTO fetch(String searchWord);
}

package com.smartpath.datacollector.service;


import com.smartpath.datacollector.model.GeneralResponseDTO;

import java.util.List;

public interface InfoService {
    List<GeneralResponseDTO> getInfo(String searchWord, String type) throws InterruptedException;
}

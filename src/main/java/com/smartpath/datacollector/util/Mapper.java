package com.smartpath.datacollector.util;


import com.smartpath.datacollector.model.DataResponseDTO;
import com.smartpath.datacollector.model.GeneralResponseDTO;
import com.smartpath.datacollector.model.Media;
import com.smartpath.datacollector.model.google.GoogleResponseDTO;
import com.smartpath.datacollector.model.itunes.ItunesResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class Mapper {
    public static GeneralResponseDTO mapGoogleResponseToGeneralResponse(ResponseEntity<GoogleResponseDTO> googleResponse){
        GeneralResponseDTO generalResponseDTO = new GeneralResponseDTO();
        List<DataResponseDTO> dataList = new ArrayList<>();
        googleResponse.getBody().getItems().forEach(itemDTO -> {
            DataResponseDTO dataResponse = new DataResponseDTO();
            dataResponse.setTitle(itemDTO.getVolumeInfo().getTitle());
            dataResponse.setAuthors(itemDTO.getVolumeInfo().getAuthors());
            dataList.add(dataResponse);
            generalResponseDTO.setData(dataList);
            generalResponseDTO.setType(Media.BOOK);
        });
        Collections.sort(dataList, Comparator.comparing(DataResponseDTO::getTitle));
        return generalResponseDTO;
    }
    public static GeneralResponseDTO mapItunesResponseToGeneralResponse(ResponseEntity<ItunesResponseDTO> itunesResponse){
        GeneralResponseDTO generalResponseDTO = new GeneralResponseDTO();
        List<DataResponseDTO> dataList = new ArrayList<>();
        itunesResponse.getBody().getResults().forEach(infoDTO -> {
            DataResponseDTO dataResponse = new DataResponseDTO();
            dataResponse.setTitle(infoDTO.getTrackName());
            dataResponse.setAuthors(Arrays.asList(infoDTO.getArtistName()));
            dataList.add(dataResponse);
            generalResponseDTO.setData(dataList);
            generalResponseDTO.setType(Media.MUSIC);
        });
        Collections.sort(dataList, Comparator.comparing(DataResponseDTO::getTitle));
        return generalResponseDTO;
    }

}

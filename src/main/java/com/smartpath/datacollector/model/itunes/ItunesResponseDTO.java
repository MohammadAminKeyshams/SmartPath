package com.smartpath.datacollector.model.itunes;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItunesResponseDTO {
    private List<InfoDTO> results;
}

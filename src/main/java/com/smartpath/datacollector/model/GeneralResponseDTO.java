package com.smartpath.datacollector.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GeneralResponseDTO {
    private List<DataResponseDTO> data;
    private String type;
}

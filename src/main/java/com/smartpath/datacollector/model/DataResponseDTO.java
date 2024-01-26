package com.smartpath.datacollector.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataResponseDTO {
    private String title;
    private List<String> authors;
}

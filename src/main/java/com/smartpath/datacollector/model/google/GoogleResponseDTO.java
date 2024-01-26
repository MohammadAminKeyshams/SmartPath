package com.smartpath.datacollector.model.google;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GoogleResponseDTO {
    List<ItemDTO> items;
}

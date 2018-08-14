package com.laa.nolasa.laanolasa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InfoXSearchResult {

    private String[] libraIDs = new String[15];
    private InfoXSearchStatus status;

}

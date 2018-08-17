package com.laa.nolasa.laanolasa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InfoXSearchResult {
    private Long[] libraIDs ;
    private InfoXSearchStatus status;
}

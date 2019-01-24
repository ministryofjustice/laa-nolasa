package com.laa.nolasa.laanolasa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class InfoXSearchResult {
    public static final int MAX_LIBRA_RECORDS = 15;
    private Long[] libraIDs  = new Long[15];
    private final InfoXSearchStatus status;
}

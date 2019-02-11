package com.laa.nolasa.laanolasa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class InfoXSearchResult {
    public static final int MAX_LIBRA_RECORDS = 15;
    private List<Long> libraIDs  = new ArrayList<>();
    private final InfoXSearchStatus status;
}

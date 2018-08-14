package com.laa.nolasa.laanolasa.service;

import com.laa.nolasa.laanolasa.dto.InfoXSearchResult;
import com.laa.nolasa.laanolasa.dto.InfoXSearchStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import org.springframework.stereotype.Service;

@Service
public class InfoXService {

    public InfoXSearchResult search(Nol nol) {
        Long result[] = new Long[15];
        return new InfoXSearchResult(result, InfoXSearchStatus.SUCCESS);
    }
}

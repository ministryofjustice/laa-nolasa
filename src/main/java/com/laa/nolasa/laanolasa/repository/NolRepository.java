package com.laa.nolasa.laanolasa.repository;

import com.laa.nolasa.laanolasa.entity.Nol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface NolRepository extends JpaRepository<Nol, Long> {

    default List<Nol> getNolForAutoSearch(String... statuses) {
        return findByStatusInAndRepOrdersHearingDateNotNullAndRepOrdersApplicantsFirstNameNotNullAndRepOrdersMagistrateCourtsCjsAreaCodeNotNullAndRepOrdersNolAutoSearchResultsIdNotNull(Arrays.asList(statuses));
    }

    List<Nol> findByStatusInAndRepOrdersHearingDateNotNullAndRepOrdersApplicantsFirstNameNotNullAndRepOrdersMagistrateCourtsCjsAreaCodeNotNullAndRepOrdersNolAutoSearchResultsIdNotNull(List<String> statuses);

}

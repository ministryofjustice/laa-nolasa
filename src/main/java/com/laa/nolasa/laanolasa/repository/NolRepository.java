package com.laa.nolasa.laanolasa.repository;

import com.laa.nolasa.laanolasa.common.NolStatus;
import com.laa.nolasa.laanolasa.entity.Nol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface NolRepository extends JpaRepository<Nol, Long> {

    default List<Nol> getNolForAutoSearch(NolStatus... statuses) {
        List<String> searchCritera = Arrays.asList(statuses).stream()
                .map(NolStatus::getStatus)
                .collect(Collectors.toList());

        return findByStatusInAndRepOrdersHearingDateNotNullAndRepOrdersApplicantsFirstNameNotNullAndRepOrdersMagistrateCourtsCjsAreaCodeNotNull(searchCritera);
    }

    List<Nol> findByStatusInAndRepOrdersHearingDateNotNullAndRepOrdersApplicantsFirstNameNotNullAndRepOrdersMagistrateCourtsCjsAreaCodeNotNull(List<String> statuses);

}

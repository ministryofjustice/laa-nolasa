package com.laa.nolasa.laanolasa.repository;

import com.laa.nolasa.laanolasa.entity.Nol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NolRepository extends JpaRepository <Nol, Long>{

    List<Nol> findByStatusDescriptionInAndRepOrdersHearingDateNotNullAndRepOrdersApplicantsFirstNameNotNullAndRepOrdersMagistrateCourtsCjsAreaCodeNotNull(List<String> statuses);

}

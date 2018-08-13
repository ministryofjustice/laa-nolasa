package com.laa.nolasa.laanolasa.repository;

import com.laa.nolasa.laanolasa.entity.NolAutoSearchResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NolAutoSearchResultsRepository extends JpaRepository <NolAutoSearchResults, Long>{
}

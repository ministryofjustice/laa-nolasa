package com.laa.nolasa.laanolasa;

import com.laa.nolasa.laanolasa.entity.Nol;
import com.laa.nolasa.laanolasa.repository.NolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
class GreetingController {

  @Autowired
  private NolRepository nolRepository;
  @RequestMapping("/hello/{name}")
  String hello(@PathVariable String name) {

    return "Hello, " + name + "!";
  }
  @RequestMapping("/nol")
  String getNol() {

    List<String> statuses = new ArrayList<>();
    statuses.add("NOT ON LIBRA");
    statuses.add("RESULTS REJECTED");
    List<Nol> nols = nolRepository.findByStatusDescriptionInAndRepOrdersHearingDateNotNullAndRepOrdersApplicantsFirstNameNotNullAndRepOrdersMagistrateCourtsCjsAreaCodeNotNull(statuses);

    nolRepository.saveAll(nols);
    return "Hello, " + nols;
  }

}
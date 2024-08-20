package com.codeaddi.mrc_resources.controller;

import com.codeaddi.mrc_resources.controller.db.BoatService;
import com.codeaddi.mrc_resources.model.entity.Boat;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/boats")
@RestController
@Slf4j
public class BoatController {

  @Autowired BoatService boatService;

  @GetMapping("/get_all")
  public ResponseEntity<List<Boat>> getAllBoats() {
    log.info("Retrieving all boats");
    return ResponseEntity.ok(boatService.getAllBoats());
  }
}

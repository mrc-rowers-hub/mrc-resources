package com.codeaddi.mrc_resources.controller;

import com.codeaddi.mrc_resources.controller.service.ResourceService;
import com.codeaddi.mrc_resources.controller.service.db.BoatService;
import com.codeaddi.mrc_resources.controller.util.DateUtil;
import com.codeaddi.mrc_resources.model.enums.EquipmentType;
import com.codeaddi.mrc_resources.model.repository.entity.Boat;

import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/boats")
@RestController
@Slf4j
public class BoatController {

  @Autowired BoatService boatService;
  @Autowired private ResourceService resourceService;
  EquipmentType equipmentType = EquipmentType.BOAT;


  @GetMapping("/get_all")
  public ResponseEntity<List<Boat>> getAllBoats() {
    log.info("Retrieving all boats");
    return ResponseEntity.ok(boatService.getAllBoats());
  }

  @GetMapping("/available")
  public ResponseEntity<?> getBladesAvailableAtTime(// returns all resources, but inUseOnDate shows if they're in use at the specified date/time, and if that's not null - details of the use
          @RequestParam String date,
          @RequestParam(required = false) String from,
          @RequestParam(required = false) String to) {

    return resourceService.getResourceInUseStatus(date, from, to, equipmentType);
  }
}

package com.codeaddi.mrc_resources.controller;

import com.codeaddi.mrc_resources.controller.service.db.BladeService;
import com.codeaddi.mrc_resources.model.repository.entity.Blade;

import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/blades")
@RestController
@Slf4j
public class BladeController {

  @Autowired BladeService bladeService;

  @GetMapping("/get_all")
  public ResponseEntity<List<Blade>> getAllBlades() {
    log.info("Retrieving all blades");
    return ResponseEntity.ok(bladeService.getAllBlades());
  }

//   get all at time
  @GetMapping("/available")
  public void getBladesAvailableAtTime(@RequestParam LocalDateTime localDateTime){
    // extract thje date, and the time, from localdate time
    // get all blades
    // get all BLADES in use for this date
    // if there are blades in use for this date - check the time
    // if any in use, work that out

  }

  // record blade in use

  // do the same for boats too
}

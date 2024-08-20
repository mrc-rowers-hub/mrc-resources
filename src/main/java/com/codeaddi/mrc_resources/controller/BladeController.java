package com.codeaddi.mrc_resources.controller;

import com.codeaddi.mrc_resources.controller.db.BladeService;
import com.codeaddi.mrc_resources.model.repository.entity.Blade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/blades")
@RestController
@Slf4j
public class BladeController {

  @Autowired
  BladeService bladeService;

  @GetMapping("/get_all")
  public ResponseEntity<List<Blade>> getAllBlades() {
    log.info("Retrieving all blades");
    return ResponseEntity.ok(bladeService.getAllBlades());
  }
}

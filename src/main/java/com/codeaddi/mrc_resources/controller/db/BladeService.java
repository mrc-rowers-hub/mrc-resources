package com.codeaddi.mrc_resources.controller.db;

import com.codeaddi.mrc_resources.model.repository.BladeRepository;
import com.codeaddi.mrc_resources.model.repository.entity.Blade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BladeService {
  @Autowired
  BladeRepository bladeRepository;

  public List<Blade> getAllBlades() {
    return bladeRepository.findAll();
  }
}

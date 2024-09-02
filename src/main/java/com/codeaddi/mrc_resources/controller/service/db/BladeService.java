package com.codeaddi.mrc_resources.controller.service.db;

import com.codeaddi.mrc_resources.model.repository.BladeRepository;
import com.codeaddi.mrc_resources.model.repository.entity.Blade;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BladeService {
  @Autowired BladeRepository bladeRepository;

  public List<Blade> getAllBlades() {
    return bladeRepository.findAll();
  }
}

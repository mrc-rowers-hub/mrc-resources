package com.codeaddi.mrc_resources.controller.service.db;

import com.codeaddi.mrc_resources.model.repository.BoatsRepository;
import com.codeaddi.mrc_resources.model.repository.entity.Boat;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BoatService {
  @Autowired BoatsRepository boatsRepository;

  public List<Boat> getAllBoats() {
    return boatsRepository.findAll();
  }
}

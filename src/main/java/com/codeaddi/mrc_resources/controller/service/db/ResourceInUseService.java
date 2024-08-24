package com.codeaddi.mrc_resources.controller.service.db;

import com.codeaddi.mrc_resources.model.enums.EquipmentType;
import com.codeaddi.mrc_resources.model.repository.BladeRepository;
import com.codeaddi.mrc_resources.model.repository.ResourceInUseRepository;
import com.codeaddi.mrc_resources.model.repository.entity.Blade;
import com.codeaddi.mrc_resources.model.repository.entity.ResourceInUse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ResourceInUseService {
  @Autowired
  ResourceInUseRepository resourceInUseRepository;

  public List<ResourceInUse> getAllResourcesInUse() {
    return resourceInUseRepository.findAll();
  }

  public List<ResourceInUse> getAllBladesInUse(){
    return getAllResourcesInUse().stream().filter(resource -> resource.getEquipmentType().equals(EquipmentType.BLADE)).toList();
  }

  public List<ResourceInUse> getAllBoatsInUse(){
    return getAllResourcesInUse().stream().filter(resource -> resource.getEquipmentType().equals(EquipmentType.BOAT)).toList();
  }

  public List<ResourceInUse> getAllBladesInUseForDate(Date date){
    return getAllBladesInUse().stream().filter(resource -> resource.getDate().equals(date)).toList();
  }


}

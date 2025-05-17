package com.codeaddi.mrc_resources.controller.service.db;

import com.codeaddi.mrc_resources.model.enums.EquipmentType;
import com.codeaddi.mrc_resources.model.repository.ResourceInUseRepository;
import com.codeaddi.mrc_resources.model.repository.entity.ResourceInUse;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResourceInUseService {
  @Autowired ResourceInUseRepository resourceInUseRepository;


  public List<ResourceInUse> getAllResourcesInUse() {
    return resourceInUseRepository.findAll();
  }

  public List<ResourceInUse> getAllResourcesInUseWithinTimePeriod(Date dateParsed, LocalTime fromTime, LocalTime toTime){
      List<ResourceInUse> resourcesStartingWithinSession = resourceInUseRepository.findByDateAndEndTimeAfter(dateParsed, fromTime);
      List<ResourceInUse> resourcesEndingWithinSession = resourceInUseRepository.findByDateAndStartTimeBefore(dateParsed, toTime);

      List<ResourceInUse> combined = new ArrayList<>(resourcesStartingWithinSession);
      combined.addAll(resourcesEndingWithinSession);
// now filter for unique
      HashSet<Long> seenIds = new HashSet<>();
      List<ResourceInUse> uniqueList = new ArrayList<>();

      for (ResourceInUse resource : combined) {
          if (resource.getId() != null && seenIds.add(resource.getId())) {
              uniqueList.add(resource);
          }
      }
      return uniqueList;
  }

  private List<ResourceInUse> getAllBladesInUse() {
    return getAllResourcesInUse().stream()
        .filter(resource -> resource.getEquipmentType().equals(EquipmentType.BLADE))
        .toList();
  }

  private List<ResourceInUse> getAllBoatsInUse() {
    return getAllResourcesInUse().stream()
        .filter(resource -> resource.getEquipmentType().equals(EquipmentType.BOAT))
        .toList();
  }

  public List<ResourceInUse> getAllResourceInUseForDate(Date date, EquipmentType equipmentType) {
      return switch (equipmentType) {
          case EquipmentType.BLADE -> getAllBladesInUse().stream()
                  .filter(resource -> resource.getDate().equals(date))
                  .toList();
          case EquipmentType.BOAT ->
                  getAllBoatsInUse().stream().filter(resource -> resource.getDate().equals(date)).toList();
      };

  }
}

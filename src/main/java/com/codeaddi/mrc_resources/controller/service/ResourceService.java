package com.codeaddi.mrc_resources.controller.service;

import com.codeaddi.mrc_resources.controller.service.db.BladeService;
import com.codeaddi.mrc_resources.controller.service.db.ResourceInUseService;
import com.codeaddi.mrc_resources.model.http.ResourceUseDTO;
import com.codeaddi.mrc_resources.model.repository.entity.Blade;
import com.codeaddi.mrc_resources.model.repository.entity.ResourceInUse;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResourceService { // Todo, maybe delete this one

  @Autowired BladeService bladeService;

  @Autowired ResourceInUseService resourceInUseService;

  public List<ResourceUseDTO<Object>> getBladesForDate(
      Date date) { // Todo take in type opf resource and filter by that

    List<Blade> allBlades = bladeService.getAllBlades();
    List<ResourceInUse> bladesInUseOnThisDate = resourceInUseService.getAllBladesInUseForDate(date);

    List<ResourceUseDTO<Object>> allBladesOnDate = new ArrayList<>();

    for (Blade blade : allBlades) {
      List<ResourceInUse> resourcesInUseOnDay =
          getResourcesInUseForThisBlade(blade, bladesInUseOnThisDate);
      allBladesOnDate.add(
          ResourceUseDTO.builder().resource(blade).inUseOnDate(resourcesInUseOnDay).build());
    }
    return allBladesOnDate;
  }

  private List<ResourceInUse> getResourcesInUseForThisBlade(
      Blade blade, List<ResourceInUse> bladesInUseOnThisDate) {
    return bladesInUseOnThisDate.stream()
        .filter(resource -> resource.getResource_id().equals(blade.getId()))
        .toList();
  }
}

package com.codeaddi.mrc_resources.model.http;

import com.codeaddi.mrc_resources.model.repository.entity.ResourceInUse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ResourceUseDTO<T> {
//  private EquipmentType equipmentType;
//  private ResourceStatus resourceStatus;
//  private LocalDateTime localDateTime;
//  private LocalDateTime nextUse;

  private T resource;
  private List<ResourceInUse> inUseOnDate;


}

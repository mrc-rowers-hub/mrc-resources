package com.codeaddi.mrc_resources.model.http;

import com.codeaddi.mrc_resources.model.repository.entity.ResourceInUse;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResourceUseDTO<T> {
  private T resource;
  private List<ResourceInUse> inUseOnDate;
}

package com.codeaddi.mrc_resources.model.http;

import com.codeaddi.mrc_resources.model.enums.EquipmentType;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Jacksonized
public class ResourceUseDTO {
    private EquipmentType equipmentType;
    private ResourceStatus resourceStatus;
    private LocalDateTime localDateTime;
    private LocalDateTime nextUse;
}

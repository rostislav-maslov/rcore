package com.rcore.domain.device.port.filters;

import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.device.entity.DeviceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class DeviceFilters extends SearchFilters {
    private String credentialId;
    private String operatingSystemId;
    private DeviceEntity.Type type;
    private String buildVersion;
}

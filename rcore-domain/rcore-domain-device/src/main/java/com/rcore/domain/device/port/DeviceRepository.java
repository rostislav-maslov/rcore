package com.rcore.domain.device.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.rcore.domain.commons.port.dto.SearchResult;
import com.rcore.domain.device.entity.DeviceEntity;
import com.rcore.domain.device.port.filters.DeviceFilters;

import java.util.Optional;

public interface DeviceRepository extends CRUDRepository<String, DeviceEntity, DeviceFilters> {
    Optional<DeviceEntity> findByToken(String token);
    SearchResult<DeviceEntity> findByCredentialId(String credentialId);
    SearchResult<DeviceEntity> findByOperatingSystemId(String operatingSystemId);
}

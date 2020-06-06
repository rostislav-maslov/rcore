package com.rcore.admincore.domain.user.web.api;

import com.rcore.restapi.web.api.request.SearchApiRequest;
import lombok.Data;

@Data
public class SearchWithFilters extends SearchApiRequest {
    private String roleId;
}

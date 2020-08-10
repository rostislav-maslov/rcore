package com.rcore.restapi.web.endpoints;

import com.rcore.restapi.web.api.response.OkApiResponse;
import com.rcore.restapi.web.api.response.SuccessApiResponse;
import com.rcore.restapi.web.endpoints.routes.MonitoringRoutes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringEndpoints {

    /**
     * Проверка работаспособности приложения
     * @return
     */
    @GetMapping(value = MonitoringRoutes.AVAILABILITY)
    public OkApiResponse life() {
        return OkApiResponse.of();
    }

}

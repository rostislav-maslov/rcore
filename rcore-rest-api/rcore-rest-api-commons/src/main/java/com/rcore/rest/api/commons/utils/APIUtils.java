package com.rcore.rest.api.commons.utils;

import com.rcore.rest.api.commons.request.SearchApiRequest;
import com.rcore.rest.api.commons.response.SearchApiResponse;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class APIUtils {


    /**
     * Получение всех ресурсов. Запросы делаются пачками до того момента, пока не получим все ресурсы.
     *
     * @param apiMethod       - метод обращения в API
     * @param <ResourceModel> - Модель ресурса из API
     * @return все ресурсы
     */
    public static <ResourceModel, Request extends SearchApiRequest> List<ResourceModel> findAllResources(
            Function<Request, SuccessApiResponse<SearchApiResponse<ResourceModel>>> apiMethod,
            Request request
    ) throws APIMethodNotAvailableException {
        List<ResourceModel> result = new ArrayList<>();
        Long offset = 0L;
        Long resultCount = -1L;
        while (result.size() != resultCount) {
            request.setOffset(offset);
            SearchApiResponse<ResourceModel> resources = sendRequest(apiMethod, request).getResult();

            result.addAll(resources.getItems());
            resultCount = resources.getCount();

            offset += request.getLimit();
        }

        return result;
    }

    private static <ResourceModel, Request extends SearchApiRequest> SuccessApiResponse<SearchApiResponse<ResourceModel>> sendRequest(
            Function<Request, SuccessApiResponse<SearchApiResponse<ResourceModel>>> apiMethod,
            Request request
    ) throws APIMethodNotAvailableException {
        try {
            return apiMethod.apply(request);
        } catch (Exception e) {
            log.error("Send find all request exception", e);
            throw new APIMethodNotAvailableException();
        }
    }

    public static class APIMethodNotAvailableException extends Exception {
    }


}

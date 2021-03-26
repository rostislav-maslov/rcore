package com.rcore.rest.api.commons.utils;

import com.rcore.commons.mapper.ExampleDataMapper;
import com.rcore.rest.api.commons.response.SearchApiResponse;
import com.rcore.rest.api.commons.response.SuccessApiResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class APIUtils {


    /**
     * Получение всех ресурсов. Запросы делаются пачками до того момента, пока не получим все ресурсы.
     *
     * @param limit - кол-во ресурсов, получаемое для одного запроса
     * @param apiMethod - метод обращения в API
     * @param <ResourceModel> - Модель ресурса из API
     * @return все ресурсы
     */
    public static <ResourceModel> List<ResourceModel> findAllResources(
            Long limit,
            BiFunction<Long, Long, SuccessApiResponse<SearchApiResponse<ResourceModel>>> apiMethod
    ) {
        List<ResourceModel> result = new ArrayList<>();
        Long offset = 0L;
        Long resultCount = -1L;
        while (result.size() != resultCount) {
            SearchApiResponse<ResourceModel> resources = apiMethod.apply(limit, offset).getResult();
            result.addAll(resources.getItems());

            if (resultCount < 0)
                resultCount = resources.getCount();

            offset += limit;
        }

        return result;
    }

    public static <ResourceModel> List<ResourceModel> findAllResources(
            BiFunction<Long, Long, SuccessApiResponse<SearchApiResponse<ResourceModel>>> apiMethod
    ) {
        return findAllResources(20L, apiMethod);
    }


}

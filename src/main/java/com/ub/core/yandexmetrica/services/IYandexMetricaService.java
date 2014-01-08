package com.ub.core.yandexmetrica.services;

import com.ub.core.yandexmetrica.models.YandexMetricaDoc;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;


public interface IYandexMetricaService    extends PagingAndSortingRepository<YandexMetricaDoc, Long> {
}

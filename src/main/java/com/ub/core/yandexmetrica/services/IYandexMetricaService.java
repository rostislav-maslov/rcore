package com.ub.core.yandexmetrica.services;

import com.ub.core.yandexmetrica.models.YandexMetricaDoc;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IYandexMetricaService    extends PagingAndSortingRepository<YandexMetricaDoc, Long> {
}

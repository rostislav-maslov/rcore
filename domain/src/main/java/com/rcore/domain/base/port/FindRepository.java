package com.rcore.domain.base.port;

import java.util.List;

public interface FindRepository<Entity, Query> {

    List<Entity> find(Query query);

    SearchResult<Entity> findResult(Query query);

}

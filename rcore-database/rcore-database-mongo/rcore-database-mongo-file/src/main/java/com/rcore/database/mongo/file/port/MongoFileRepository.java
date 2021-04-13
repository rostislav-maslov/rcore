package com.rcore.database.mongo.file.port;

import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.database.mongo.file.model.FileDoc;
import com.rcore.database.mongo.file.query.FindByPath;
import com.rcore.database.mongo.file.query.FindWithFiltersFileQuery;
import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.port.dto.SearchResult;
import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.port.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Repository
public class MongoFileRepository implements FileRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<FileEntity> findByPath(String path) {
        return Optional.ofNullable(mongoTemplate.findOne(FindByPath.of(path).getQuery(), FileDoc.class));
    }

    @Override
    public FileEntity save(FileEntity object) {
        return mongoTemplate.save(object, CollectionNameUtils.getCollectionName(FileDoc.class));
    }

//    @Override
//    public Boolean delete(FileEntity object) {
//        long deleteCount = mongoTemplate.remove(object, CollectionNameUtils.getCollectionName(FileDoc.class)).getDeletedCount();
//        return deleteCount > 0;
//    }

    @Override
    public Boolean delete(String id) {
        long deleteCount = mongoTemplate.remove(Query.query(Criteria.where("_id").is(id)), CollectionNameUtils.getCollectionName(FileDoc.class)).getDeletedCount();
        return deleteCount > 0;
    }

    @Override
    public Optional<FileEntity> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, FileDoc.class));
    }

    @Override
    public SearchResult<FileEntity> find(SearchFilters request) {
        Query query = new FindWithFiltersFileQuery(request).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, FileDoc.class)
                        .stream()
                        .collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), FileDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), FileDoc.class);
    }
}

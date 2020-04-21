package com.rcore.database.mongo.domain.file.port;

import com.rcore.database.mongo.common.utils.CollectionNameUtils;
import com.rcore.database.mongo.domain.file.model.FileDoc;
import com.rcore.database.mongo.domain.file.query.FindByPath;
import com.rcore.domain.base.port.SearchResult;
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
public class FileRepositoryImpl implements FileRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<FileEntity> findByPath(String path) {
        return Optional.ofNullable(mongoTemplate.findOne(FindByPath.of(path).getQuery(), FileDoc.class));
    }

    @Override
    public FileEntity save(FileEntity object) {
        return mongoTemplate.save(object, CollectionNameUtils.getCollectionName(FileDoc.class));
    }

    @Override
    public Boolean delete(FileEntity object) {
        long deleteCount = mongoTemplate.remove(object, CollectionNameUtils.getCollectionName(FileDoc.class)).getDeletedCount();
        return deleteCount > 0;
    }

    @Override
    public Boolean deleteById(String id) {
        long deleteCount = mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), CollectionNameUtils.getCollectionName(FileDoc.class)).getDeletedCount();
        return deleteCount > 0;
    }

    @Override
    public Optional<FileEntity> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, FileDoc.class));
    }

    @Override
    public SearchResult<FileEntity> find(Long size, Long skip) {
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(new Query().skip(skip).limit(size.intValue()), FileDoc.class)
                        .stream()
                        .collect(Collectors.toList()),
                count()
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), FileDoc.class);
    }
}

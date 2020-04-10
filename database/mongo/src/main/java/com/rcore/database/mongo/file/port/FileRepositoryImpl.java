package com.rcore.database.mongo.file.port;

import com.rcore.database.mongo.file.port.query.FindByPath;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.port.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class FileRepositoryImpl implements FileRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<FileEntity> findByPath(String path) {
        return Optional.ofNullable(mongoTemplate.findOne(FindByPath.of(path).getQuery(), FileEntity.class));
    }

    @Override
    public FileEntity save(FileEntity object) {
        return mongoTemplate.save(object);
    }

    @Override
    public Boolean delete(FileEntity object) {
        long deleteCount = mongoTemplate.remove(object).getDeletedCount();
        return deleteCount > 0;
    }

    @Override
    public Boolean deleteById(String id) {
        long deleteCount = mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), FileEntity.class).getDeletedCount();
        return deleteCount > 0;
    }

    @Override
    public Optional<FileEntity> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, FileEntity.class));
    }

    @Override
    public SearchResult<FileEntity> find(Long size, Long skip) {
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(new Query().skip(skip).limit(size.intValue()), FileEntity.class),
                count()
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), FileEntity.class);
    }
}

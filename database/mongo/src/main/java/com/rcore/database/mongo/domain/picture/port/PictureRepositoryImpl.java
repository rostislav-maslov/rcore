package com.rcore.database.mongo.domain.picture.port;

import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PictureRepositoryImpl implements PictureRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public PictureEntity save(PictureEntity object) {
        return mongoTemplate.save(object);
    }

    @Override
    public Boolean delete(PictureEntity object) {
        long deletedCount = mongoTemplate.remove(object).getDeletedCount();
        return deletedCount > 0;
    }

    @Override
    public Boolean deleteById(String id) {
        long deletedCount = mongoTemplate.remove(Query.query(Criteria.where("id").is(id))).getDeletedCount();
        return deletedCount > 0;
    }

    @Override
    public Optional<PictureEntity> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, PictureEntity.class));
    }

    @Override
    public SearchResult<PictureEntity> find(Long size, Long skip) {
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(new Query().limit(size.intValue()).skip(skip), PictureEntity.class),
                count()
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), PictureEntity.class);
    }
}

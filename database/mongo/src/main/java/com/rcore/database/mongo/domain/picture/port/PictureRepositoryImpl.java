package com.rcore.database.mongo.domain.picture.port;

import com.rcore.database.mongo.common.utils.CollectionNameUtils;
import com.rcore.database.mongo.domain.picture.model.PictureDoc;
import com.rcore.database.mongo.domain.picture.query.FindAllWithSearch;
import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class PictureRepositoryImpl implements PictureRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public PictureEntity save(PictureEntity object) {
        return mongoTemplate.save(object, CollectionNameUtils.getCollectionName(PictureDoc.class));
    }

    @Override
    public Boolean delete(PictureEntity object) {
        long deletedCount = mongoTemplate.remove(object, CollectionNameUtils.getCollectionName(PictureDoc.class)).getDeletedCount();
        return deletedCount > 0;
    }

    @Override
    public Boolean deleteById(String id) {
        long deletedCount = mongoTemplate.remove(Query.query(Criteria.where("_id").is(id)), CollectionNameUtils.getCollectionName(PictureDoc.class)).getDeletedCount();
        return deletedCount > 0;
    }

    @Override
    public Optional<PictureEntity> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, PictureDoc.class, CollectionNameUtils.getCollectionName(PictureDoc.class)));
    }

    @Override
    public SearchResult<PictureEntity> find(SearchRequest request) {
        Query query = new FindAllWithSearch(request).getQuery();
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, PictureDoc.class)
                        .stream()
                        .collect(Collectors.toList()),
                mongoTemplate.count(query,PictureDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), PictureDoc.class);
    }
}

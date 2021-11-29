package com.rcore.database.mongo.picture.port;


import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.database.mongo.picture.model.PictureDoc;
import com.rcore.database.mongo.picture.query.DeleteUnused;
import com.rcore.database.mongo.picture.query.FindAllWithSearch;
import com.rcore.database.mongo.picture.query.FindByPath;
import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.port.dto.SearchResult;
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

//    @Override
//    public Boolean delete(PictureEntity object) {
//        long deletedCount = mongoTemplate.remove(object, CollectionNameUtils.getCollectionName(PictureDoc.class)).getDeletedCount();
//        return deletedCount > 0;
//    }

    @Override
    public Boolean delete(String id) {
        long deletedCount = mongoTemplate.remove(Query.query(Criteria.where("_id").is(id)), CollectionNameUtils.getCollectionName(PictureDoc.class)).getDeletedCount();
        return deletedCount > 0;
    }

    @Override
    public Optional<PictureEntity> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, PictureDoc.class, CollectionNameUtils.getCollectionName(PictureDoc.class)));
    }

    @Override
    public SearchResult<PictureEntity> find(SearchFilters request) {
        Query query = new FindAllWithSearch(request).getQuery();
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, PictureDoc.class)
                        .stream()
                        .collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), PictureDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), PictureDoc.class);
    }

    @Override
    public void deleteUnused() {
        mongoTemplate.remove(new DeleteUnused(), CollectionNameUtils.getCollectionName(PictureDoc.class));
    }

    @Override
    public boolean exist(String s) {
        return false;
    }

    @Override
    public Optional<PictureEntity> findByPath(String path) {
        return Optional.ofNullable(mongoTemplate.findOne(FindByPath.of(path).getQuery(), PictureDoc.class));
    }
}

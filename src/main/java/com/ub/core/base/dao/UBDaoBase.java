package com.ub.core.base.dao;


import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



/**
 * Created with IntelliJ IDEA.
 * User: Антон
 * Date: 07.10.13
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */
public class UBDaoBase<T, PK extends Serializable>
        implements IUBDaoBase<T, PK> {
    protected MongoTemplate mongoTemplate;

    protected Class<T> type;

    public UBDaoBase(Class<T> type) {
        this.type = type;
    }

    @Override
    public void create(T newInstance) {
        getMongoTemplate().insert(newInstance);
    }

    @Override
    public T read(PK id) {
        return getMongoTemplate().findById(id, type);
    }

    @Override
    public void update(T transientObject) {
        getMongoTemplate().save(transientObject);
    }

    @Override
    public void delete(T persistentObject) {
        getMongoTemplate().remove(persistentObject);
    }

    @Override
    public List<T> getAll() {
        //To change body of implemented methods use File | Settings | File Templates.
        return getMongoTemplate().findAll(type);
    }

    @Override
    public List<T> getByQuery(Query query) {
        return getMongoTemplate().find(query, type);
    }

    @Override
    public long getCountByQuery(Query query) {
        return  (long) getMongoTemplate().count(query, type);
    }

    @Override
    public List<T> findByParams(Map<String, List<String>> mapSearch) {

        Query query = new Query();//= getDateCriteria(new Date());
        Iterator iterator = mapSearch.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<String>> mapEntry = (Map.Entry<String, List<String>>) iterator.next();
            query.addCriteria(Criteria.where(mapEntry.getKey()).in(mapEntry.getValue()));

        }
        return getMongoTemplate().find(query, this.type);

    }

    @Override
    public T getByQueryUnique(Query query) {
        query.limit(1);
        return getMongoTemplate().findOne(query, type);
    }

    /**
     ************GENERATING***********************************
     */
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}

package com.ub.core.base.dao;


import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Антон
 * Date: 07.10.13
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */
public interface IUBDaoBase<T, PK extends Serializable> {


    /**
     * Save in database
     * @param newInstance объект
     * @return
     */
    void create(T newInstance);

    /**
     * read from database
     * @param id идентификатор объекта
     * @return объект их БД
     */
    T read(PK id);

    /**
     * update from database
     * @param transientObject
     */
    void update(T transientObject);

    /**
     * delete from database
     * @param persistentObject
     */
    void delete(T persistentObject);



    List<T> getAll();

    List<T> getByQuery(Query query);

    T getByQueryUnique(Query query);

    long getCountByQuery(Query query);

    public List<T> findByParams(Map<String, List<String>> mapSearch) ;
}

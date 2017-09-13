package com.zhuravchak.dao;

import com.zhuravchak.entity.Entity;
import java.util.List;

/**
 * Created by Yaroslav on 05-Sep-17.
 */
public interface AbstractDAO <K, T extends Entity, V extends Entity> {

     boolean add(T entity);
     boolean addForEntity(V entity, String str);

     List<T> getAll();
     List<T> getAllForEntity(V entity);
     T getById(K id);

     boolean delete(K id);
     boolean delete(T entity);
     boolean deleteForEntity(V entity);
}

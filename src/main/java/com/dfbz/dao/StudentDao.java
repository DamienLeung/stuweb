package com.dfbz.dao;

import com.dfbz.pojo.Student;

import java.util.List;

public interface StudentDao {

    <T> void delById(Integer id, Class<T> tClass);

    void save(Object obj);

    <T>List<T> getListById(Class<T> tClass);

    void add(Object obj);

    <T> void delByIds(Integer[] id, Class<T> tClass);

}

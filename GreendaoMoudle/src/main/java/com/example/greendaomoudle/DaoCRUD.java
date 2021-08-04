package com.example.greendaomoudle;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

public interface DaoCRUD<T> {

    //添加一条数据
    boolean insert(T t);
    //添加列表数据
    boolean inserts(List<T> datas);
    //删除一条数据
    boolean delete(T t);
    //删除列表数据
    boolean deletes(List<T>datas);
    //修改一条数据
    boolean update(T t);
    //修改列表数据
    boolean updates(List<T> datas);
    //查询一条数据
    T select(WhereCondition whereCondition);
    //查询列表数据
    List<T>selectAll();
}

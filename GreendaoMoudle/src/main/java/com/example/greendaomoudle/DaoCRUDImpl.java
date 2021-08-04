package com.example.greendaomoudle;

import org.greenrobot.greendao.query.WhereCondition;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class DaoCRUDImpl<T> implements DaoCRUD<T> {

    private Class clazz;

    public DaoCRUDImpl(){
        //获取范型类型
        ParameterizedType parameterizedType = (ParameterizedType)
                getClass().getGenericSuperclass();
        clazz = (Class) parameterizedType.
                getActualTypeArguments()[0];
    }

    @Override
    public boolean insert(T t) {
        return DaoSessionUtils.getInstance().getSession().insert(t)>0;
    }

    @Override
    public boolean inserts(List<T> datas) {
        boolean flag = true;
        for (T t : datas){
            flag = insert(t);
            if (!flag)
                break;
        }
        return flag;
    }

    @Override
    public boolean delete(T t) {
        DaoSessionUtils.getInstance().getSession().delete(t);
        return true;
    }

    @Override
    public boolean deletes(List<T> datas) {
        for (T t : datas)
            delete(t);
        return true;
    }

    @Override
    public boolean update(T t) {
        return DaoSessionUtils.getInstance().getSession().insertOrReplace(t)>0;
    }

    @Override
    public boolean updates(List<T> datas) {
        boolean flag = true;
        for (T t:datas){
            flag = update(t);
            if (!flag)
                break;
        }
        return flag;
    }

    @Override
    public T select(WhereCondition whereCondition) {
        T t;
        t = (T) DaoSessionUtils.getInstance().getSession()
                .queryBuilder(clazz).where(whereCondition).unique();
        return t;
    }

    @Override
    public List<T> selectAll() {
        return DaoSessionUtils.getInstance().getSession().queryBuilder(clazz).list();
    }
}

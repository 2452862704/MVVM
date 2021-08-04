package com.example.greendaomoudle;

import org.greenrobot.greendao.AbstractDaoSession;

public class DaoSessionUtils {

    private DaoSessionUtils(){}

    private static DaoSessionUtils instance;

    public static DaoSessionUtils getInstance() {
        if (instance == null)
            instance = new DaoSessionUtils();
        return instance;
    }

    private final static String DBNAME = "catch.db";

    private AbstractDaoSession session;

    public void initDaoSession(AbstractDaoSession session){
        this.session = session;
    }

    public AbstractDaoSession getSession() {
        return session;
    }
}

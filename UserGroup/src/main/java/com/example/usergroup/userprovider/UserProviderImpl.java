package com.example.usergroup.userprovider;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.Utils;
import com.example.greendaomoudle.DaoSessionUtils;
import com.example.mvvmcommon.arouterprovider.UserProvider;
import com.example.usergroup.dao.DaoMaster;
import com.example.usergroup.dao.DaoSession;
import com.example.usergroup.data.entity.UserDaoEntity;
import com.example.usergroup.data.entity.UserEntityValues;

import java.util.List;

@Route(path = "/usergroup/userprovider")
public class UserProviderImpl implements UserProvider {

    @Override
    public int getUserId() {
        int id = 0;
        List<UserEntityValues> list = new UserDaoEntity().selectAll();
        if (list.size()>0) {
            UserEntityValues values = new UserDaoEntity().selectAll().get(0);
            id = (int) values.id;
        }
        return id;
    }

    @Override
    public void init(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(Utils.getApp(),"user.db");
        DaoMaster master = new DaoMaster(helper.getWritableDatabase());
        DaoSession session = master.newSession();
        DaoSessionUtils.getInstance().initDaoSession(session);
    }
}

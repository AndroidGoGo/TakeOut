package com.lzq.takeout.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.lzq.takeout.model.bean.User;

import java.sql.SQLException;

/**
 * Created by ${廖昭启} on 2017/6/8.
 */

public class TakeOutOpenHelpe extends OrmLiteSqliteOpenHelper {
    public TakeOutOpenHelpe(Context context) {
        super(context, "takeouts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.clearTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}

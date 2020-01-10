package com.dfbz.dao.impl;

import com.dfbz.dao.UserDao;
import com.dfbz.pojo.User;
import com.dfbz.util.JDBCUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean validateUser(User user) {
        String tableN = "user";
        StringBuilder query = new StringBuilder("select * from " + tableN);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet results = null;
        ArrayList<User> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(query.toString());
            System.out.println(query.toString());
            results = statement.executeQuery();
            while (results.next()) {
                Class tClass = User.class;
                Object o = tClass.newInstance();
                Field[] args = tClass.getDeclaredFields();
                for (Field arg :
                        args) {
                    String col = arg.getName();
                    String methodN = "set" + col.substring(0, 1).toUpperCase() + col.substring(1);
                    Method method = tClass.getDeclaredMethod(methodN, arg.getType());
                    method.invoke(o, results.getObject(col));
                }
                list.add((User)o);
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(list);
        System.out.println(user);
        if (list.contains(user))
            return true;
        else
            return false;
    }
}

package com.dfbz.dao.base;

import com.dfbz.annotation.MyAnnotation;
import com.dfbz.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDao<T> {

    public void delById(Integer id, Class<T> tClass) {
        String tableName = getTableName(tClass);
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            runner.update("delete from " + tableName + " where id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(T t) {
        Class tClass = t.getClass();
        String tableName = getTableName(tClass);
//        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        StringBuilder query = new StringBuilder("update " + tableName + " set ");
        Field[] args = tClass.getDeclaredFields();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Method getId = tClass.getDeclaredMethod("getId");
            Object id = getId.invoke(t);
            for (Field arg :
                    args) {
                arg.setAccessible(true);
                if (!arg.get(t).equals("") && arg.get(t) != null)
                    query.append(arg.getName()).append("=\"").append(arg.get(t).toString()).append("\", ");
            }
            query.delete(query.lastIndexOf(","), query.length());
            query.append(" where id = ").append(id.toString());
//            runner.update(query.toString(), tableName, id.toString());
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(query.toString());
            System.out.println(query);
            statement.execute();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, statement, connection);
        }
    }

    public List<T> getListById(Class<T> tClass) {
        String tableName = getTableName(tClass);
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            ResultSetHandler<List<T>> h = new BeanListHandler<>(tClass);
            return runner.query("select * from " + tableName, h);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(T t) {
        Class tClass = t.getClass();
        String tableName = getTableName(tClass);
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            ArrayList<String> strings = new ArrayList<>();
            Field[] args = t.getClass().getDeclaredFields();
            for (Field arg :
                    args) {
                String col = arg.getName();
                String methodN = "get" + col.substring(0, 1).toUpperCase() + col.substring(1);
                Method method = t.getClass().getDeclaredMethod(methodN);
                strings.add(method.invoke(t).toString());
            }
            runner.update("insert into " + tableName + " values(?,?,?,?)", strings.toArray());

        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void delByIds(Integer[] ids, Class<T> tClass) {
        String tableName = getTableName(tClass);
        StringBuilder query = new StringBuilder("delete from " + tableName + " where ");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            for (Integer id :
                    ids) {
                query.append("id=").append(id).append("||");
            }
            query.delete(query.lastIndexOf("||"), query.length());
            statement = connection.prepareStatement(query.toString());
            System.out.println(query.toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, statement, connection);
        }
    }

    public boolean validateUser(T t) {
        Class tClass = t.getClass();
        String tableName = getTableName(tClass);
        StringBuilder query = new StringBuilder("select * from " + tableName);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet results = null;
        ArrayList<T> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(query.toString());
            System.out.println(query.toString());
            results = statement.executeQuery();
            while (results.next()) {
                Object o = tClass.newInstance();
                Field[] args = tClass.getDeclaredFields();
                getField(tClass, results, o, args);
                list.add((T) o);
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException
                | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(results, statement, connection);
        }

        System.out.println(list);
        System.out.println(t);
        return list.contains(t);
    }

    private void getField(Class<?> tClass, ResultSet results, Object o, Field[] args)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException {
        for (Field arg :
                args) {
            String col = arg.getName();
            String methodN = "set" + col.substring(0, 1).toUpperCase() + col.substring(1);
            Method method = tClass.getDeclaredMethod(methodN, arg.getType());
            method.invoke(o, results.getObject(col));
        }
    }

    private String getTableName(Class<T> tClass) {
        MyAnnotation annotation = tClass.getAnnotation(MyAnnotation.class);
        return annotation.value();
    }
}

package com.dfbz.dao.impl;

import com.dfbz.dao.StudentDao;
import com.dfbz.pojo.Student;
import com.dfbz.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {


    @Override
    public <T> void delById(Integer id, Class<T> tClass) {
        //String tableName = "stus";

        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            runner.update("delete from stus where id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*StringBuilder query = new StringBuilder("delete from " + tableName + " where id = ?");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setInt(1, id);
            System.out.println(query);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, statement, connection);
        }*/
    }

    @Override
    public void save(Object obj) {
        String tableName = "stus";
        Class<?> tClass = obj.getClass();
//        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        StringBuilder query = new StringBuilder("update " + tableName + " set ");
        Field[] args = tClass.getDeclaredFields();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Method getId = tClass.getDeclaredMethod("getId");
            Object id = getId.invoke(obj);
            for (Field arg :
                    args) {
                arg.setAccessible(true);
                if (!arg.get(obj).equals("") && arg.get(obj) != null)
                    query.append(arg.getName()).append("=\"").append(arg.get(obj).toString()).append("\", ");
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

    @Override
    public <T>List<T> getListById(Class<T> tClass) {
        String tableName = "stus";
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        StringBuilder query = new StringBuilder("select * from " + tableName +" order by id");

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet results = null;
        try {
            /*List<Object[]> results = runner.query(query.toString(), new ArrayListHandler(), tableName);
            List<T> list = new ArrayList<T>();
            for (Object[] stu : results) {
                T obj = tClass.newInstance();
                Field[] args = tClass.getDeclaredFields();
                for (int i = 0; i < args.length; i ++) {
                    String col = args[i].getName();
                    String methodN = "set" + col.substring(0,1).toUpperCase() + col.substring(1);
                    Method method = tClass.getMethod(methodN, args[i].getType());
                    method.invoke(obj, stu[i]);
                }
            }*/
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(query.toString());
            System.out.println(query.toString());
            results = statement.executeQuery();
            List<T> list = new ArrayList<T>();
            while (results.next()) {
                T obj = tClass.newInstance();
                Field[] args = tClass.getDeclaredFields();
                for (Field arg :
                        args) {
                    String col = arg.getName();
                    String methodN = "set" + col.substring(0, 1).toUpperCase() + col.substring(1);
                    Method method = tClass.getDeclaredMethod(methodN, arg.getType());
                    method.invoke(obj, results.getObject(col));
                }
                list.add(obj);
            }
            return list;
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(results, statement, connection);
        }
        return null;
    }

    @Override
    public void add(Object obj) {
        String tableName = "stus";
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        StringBuilder query = new StringBuilder("insert into " + tableName + " values(");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(query.toString());

            Field[] args = obj.getClass().getDeclaredFields();
            for (Field arg :
                    args) {
                String col = arg.getName();
                String methodN = "get" + col.substring(0, 1).toUpperCase() + col.substring(1);
                Method method = obj.getClass().getDeclaredMethod(methodN);

                Object result = method.invoke(obj);
                if (result != null)
                    query.append("\"").append(result.toString()).append("\",");
                else
                    query.append("null ,");
            }
            query.delete(query.lastIndexOf(","), query.length());
            query.append(")");
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(query.toString());
            System.out.println(query.toString());
            statement.execute();

        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
//        } finally {
//            JDBCUtil.close(null, statement, connection);
        }
    }

    @Override
    public <T> void delByIds(Integer[] ids, Class<T> tClass) {
        String tableName = "stus";
        StringBuilder query = new StringBuilder("delete from " + tableName + " where ");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            for (Integer id:
                 ids) {
                query.append("id=").append(id).append("||");
            }
            query.delete(query.lastIndexOf("||"), query.length());
            statement = connection.prepareStatement(query.toString());
            System.out.println(query.toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

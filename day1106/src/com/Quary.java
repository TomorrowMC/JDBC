package com;

import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Yifei.Hu
 * @create 2021-11--17:39
 */
public class Quary {

    public static void ma(int k) throws Exception {
        Connection conn = JDBCUtils.connectSQL();
        String sql = "select id,name from customers where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);

//        for (int i = 0; i < args.length; i++) {
//            ps.setString(i+1, args[i]);
//        }
        ps.setInt(1, k);

        ResultSet resultSet = ps.executeQuery();
        Test1Ob test1Ob = null;
        if (resultSet.next()) {
            test1Ob = new Test1Ob();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnName = metaData.getColumnName(i + 1);
                Object objectValue = resultSet.getObject(i + 1);

                Field field = Test1Ob.class.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(test1Ob, objectValue);
            }
        }
        System.out.println(test1Ob);
        JDBCUtils.closeSQlQuery(conn, ps, resultSet);

    }


    public <T> T QuaryTest(Class<T> clazz,String sql, String... args)  {
        //获取连接,获取PreparedStatement
        Connection conn = null;
        PreparedStatement ps = null;
        T test=null;
        try {
            conn = JDBCUtils.connectSQL();
            ps = conn.prepareStatement(sql);
            //使用for循环填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //获取结果集
            ResultSet resultSet = ps.executeQuery();
            //如果是多行结果,就使用while
            if (resultSet.next()) {
                //新建一个用于接收行数据的对象
                test = clazz.newInstance();
                //获得元数据对象
                ResultSetMetaData metaData = resultSet.getMetaData();
                //使用元数据获取列数
                int columnCount = metaData.getColumnCount();
                for (int j = 0; j < columnCount; j++) {
                    // 使用结果集获取每个字段的值
                    Object object = resultSet.getObject(j + 1);
                    //使用元数据获取每个字段的label,然后使用反射给接收数据的对象赋值
                    String str = metaData.getColumnLabel(j + 1);
                    Field field = test.getClass().getDeclaredField(str);
                    field.setAccessible(true);
                    field.set(test, object);
//                System.out.println(object);
                }
                System.out.println(test);
            }
            //关闭流资源
            JDBCUtils.closeSQlQuery(conn, ps, resultSet);
        }  catch (Exception e) {
            e.printStackTrace();
        }finally {
            return  test;
        }


    }
    public <T> List<T> QuaryTest2(Class<T> clazz, String sql, String... args)  {
        List<T> result = new ArrayList<T>();
        //获取连接,获取PreparedStatement
        Connection conn = null;
        PreparedStatement ps = null;
        T test=null;
        try {
            conn = JDBCUtils.connectSQL();
            conn.getTransactionIsolation();
            
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            ps = conn.prepareStatement(sql);
            //使用for循环填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //获取结果集
            ResultSet resultSet = ps.executeQuery();
            //如果是多行结果,就使用while
            while (resultSet.next()) {
                //新建一个用于接收行数据的对象
                test = clazz.newInstance();
                //获得元数据对象
                ResultSetMetaData metaData = resultSet.getMetaData();
                //使用元数据获取列数
                int columnCount = metaData.getColumnCount();
                for (int j = 0; j < columnCount; j++) {
                    // 使用结果集获取每个字段的值
                    Object object = resultSet.getObject( j + 1);
                    //使用元数据获取每个字段的label,然后使用反射给接收数据的对象赋值
                    String str = metaData.getColumnLabel(j + 1);
                    Field field = test.getClass().getDeclaredField(str);
                    field.setAccessible(true);
                    field.set(test, object);
                }
                result.add(test);
                System.out.println(test);
            }
            //关闭流资源
            JDBCUtils.closeSQlQuery(conn, ps, resultSet);
        }  catch (Exception e) {
            e.printStackTrace();
        }finally {
            return  result;
        }


    }

    public static void main(String[] args) throws Exception {
        String sql = "select id,name from customers where id<?";
        String k = "1";
        String x= "Test1Ob";
        Quary quary = new Quary();
        List<Test1Ob> test1Obs = quary.QuaryTest2(Test1Ob.class, sql,"4");
        Iterator<Test1Ob> it = test1Obs.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }



}

package com;

import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author Yifei.Hu
 * @create 2021-11--16:12
 */
public class QueryTest {
public  void queryTest1()  throws Exception{
    int id= 0;
    String name = " ";
    //获取连接
    Connection conn=JDBCUtils.connectSQL();
    //新建SQL
    String sql="select id,name from customers where id=?";
    //获取预编译对象
    PreparedStatement preparedStatement = conn.prepareStatement(sql);
    preparedStatement.setInt(1, 1);
    //执行查询
    ResultSet resultSet = preparedStatement.executeQuery();
    //处理结果集，使用next（）,并接受返回的属性
    if (resultSet.next()) {
         id= resultSet.getInt(1);
         name = resultSet.getString(2);
    }
    //新建对象接受属性
//    Test1Ob test1Ob = new Test1Ob(id,name);
    //关闭
    JDBCUtils.closeSQlQuery(conn, preparedStatement, resultSet);
}

@Test
    public static void testOb111(String sql,String... args) throws Exception {
    Connection conn= JDBCUtils.connectSQL();
    //String sql = "select ?,? from test where id=1";
    PreparedStatement preparedStatement = conn.prepareStatement(sql);
    for (int i = 0; i < args.length; i++) {
        preparedStatement.setString(i+1, args[i]);
    }
    ResultSet resultSet = preparedStatement.executeQuery();
    ResultSetMetaData metaData = resultSet.getMetaData();
    Test1Ob test1Ob = new Test1Ob();
    if (resultSet.next()) {
        int columnCount = metaData.getColumnCount();

            for (int k = 0; k < columnCount; k++) {
                Object object=resultSet.getObject(k+1);

                String fieldName= metaData.getColumnLabel(k+1);

                Field field = Test1Ob.class.getDeclaredField(fieldName);

                field.setAccessible(true);

                field.set(test1Ob, object);
            }

    }
    JDBCUtils.closeSQlQuery(conn, preparedStatement, resultSet);
    System.out.println(test1Ob);
}

    public static void main(String[] args) throws Exception{
    QueryTest.testOb111("select ?,? from customers where id=2;", "name","id");
    }
}

package com;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Yifei.Hu
 * @create 2021-11--22:08
 */
public class LoopTest {

    public void loopTest1() throws Exception {
        Connection connection = JDBCUtils.connectSQL();
        String sql = "insert into test.loopTest(xx) values (1)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        for (int i = 0; i <= 10000;i++) {
            preparedStatement.addBatch();
            if (i==500) {
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
        }
        connection.commit();


    }

    public void loopTestClose() throws Exception {
        Connection connection = JDBCUtils.connectSQL();
        String sql = "delete from test.loopTest where 1=1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int i = preparedStatement.executeUpdate();
        System.out.println(i);
        JDBCUtils.closeSQl(connection, preparedStatement);
    }
}
